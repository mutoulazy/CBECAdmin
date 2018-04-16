package net.sppan.base.controller.admin;

import net.sppan.base.common.JsonResult;
import net.sppan.base.common.utils.FtpUtils;
import net.sppan.base.common.utils.UUIDUtils;
import net.sppan.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sound.midi.SoundbankResource;
import java.io.*;
import java.util.List;

/**
 * Created by mutoulazy on 2018/4/9.
 */
@Controller
@RequestMapping("/file")
public class FileController extends BaseController {
    @Value("${FTP_ADDRESS}")
    private String FTP_ADDRESS;
    @Value("${FTP_PORT}")
    private int FTP_PORT;
    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;
    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;
    @Value("${FTP_PRODUCTION_IMAGE_BASEPATH}")
    private String FTP_PRODUCTION_IMAGE_BASEPATH;

    @RequestMapping("/uploadTest")
    public String test(){
        return "admin/test/uploadimg";
    }

    /**
     * 单文件上传
     *
     * @param file
     * @param request
     * @return
     */
    @PostMapping("/upload")
    @ResponseBody
    public JsonResult upload(@RequestParam("upload") MultipartFile file, String CKEditorFuncNum, HttpServletRequest request, HttpSession session) {
        if (!file.isEmpty()) {
            // 上传时的文件名
            String fileName = file.getOriginalFilename();
            InputStream inputStream = null;
            try {
                inputStream = file.getInputStream();
            } catch (IOException e) {
                System.out.println("获取文件inputStream失败");
                e.printStackTrace();
            }
            // 文件大小
            long size = file.getSize();
            // 文件类型
            String end = fileName.substring(fileName.lastIndexOf("."), fileName.length());

            // 是否需要检查文件符合要求

            ServletContext servletContext = session.getServletContext();
            // 上传路径
            String path = servletContext.getRealPath("/upload");
            // 随机文件名
            String name = UUIDUtils.getUUID();

            try {
                // 上传路径文件
                boolean flag = FtpUtils.uploadFile(FTP_ADDRESS,FTP_PORT,FTP_USERNAME,FTP_PASSWORD,FTP_PRODUCTION_IMAGE_BASEPATH,"/2018",name+end,inputStream);
                System.out.println("ftp上传" + flag);
                return JsonResult.success("上传成功," + name);
            } catch (Exception e) {
                e.printStackTrace();
                return JsonResult.failure("上传失败," + e.getMessage());
            }
        } else {
            return JsonResult.failure("上传失败，因为文件为空.");
        }
    }

    /**
     * 单文件上传
     *
     * @param file
     * @param request
     * @return
     */
    @PostMapping("/ckeditorUpload")
    @ResponseBody
    public void ckeditorUpload(@RequestParam("upload") MultipartFile file, HttpServletRequest request, HttpSession session) {
        // CKEditor提交的很重要的一个参数
        String callback = request.getParameter("CKEditorFuncNum");
        if (!file.isEmpty()) {
            // 上传时的文件名
            String fileName = file.getOriginalFilename();
            InputStream inputStream = null;
            try {
                inputStream = file.getInputStream();
            } catch (IOException e) {
                System.out.println("获取文件inputStream失败");
                e.printStackTrace();
            }
            // 文件大小
            long size = file.getSize();
            // 文件类型
            String end = fileName.substring(fileName.lastIndexOf("."), fileName.length());

            // 是否需要检查文件符合要求

            ServletContext servletContext = session.getServletContext();
            // 上传路径
            String path = servletContext.getRealPath("/upload");
            // 随机文件名
            String name = UUIDUtils.getUUID();

            try {
                // 上传路径文件
                boolean flag = FtpUtils.uploadFile(FTP_ADDRESS,FTP_PORT,FTP_USERNAME,FTP_PASSWORD,FTP_PRODUCTION_IMAGE_BASEPATH,"/2018",name+end,inputStream);
                System.out.println("ftp上传" + flag);

                PrintWriter out = response.getWriter();
                out.println("<script type=\"text/javascript\">");
                out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'http://192.168.187.133/images/production/2018/" + name+end + "',''" + ")");
                out.println("</script>");
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            //
        }
    }

    /**
     * 多文件上传
     *
     * @param request
     * @return
     */
    @PostMapping("/uploadFiles")
    @ResponseBody
    public String uploadFiles(HttpServletRequest request) throws IOException {
        File savePath = new File(request.getSession().getServletContext().getRealPath("/upload/"));
        if (!savePath.exists()) {
            savePath.mkdirs();
        }
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    File saveFile = new File(savePath, file.getOriginalFilename());
                    stream = new BufferedOutputStream(new FileOutputStream(saveFile));
                    stream.write(bytes);
                    stream.close();
                } catch (Exception e) {
                    if (stream != null) {
                        stream.close();
                        stream = null;
                    }
                    return "第 " + i + " 个文件上传有错误" + e.getMessage();
                }
            } else {
                return "第 " + i + " 个文件为空";
            }
        }
        return "所有文件上传成功";
    }
}
