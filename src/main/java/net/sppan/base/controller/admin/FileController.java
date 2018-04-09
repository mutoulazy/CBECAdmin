package net.sppan.base.controller.admin;

import net.sppan.base.common.JsonResult;
import net.sppan.base.common.utils.FileUtils;
import net.sppan.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

/**
 * Created by mutoulazy on 2018/4/9.
 */
@Controller
@RequestMapping("/file")
public class FileController extends BaseController {
    @Value("${img.location}")
    private String location;

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
    public JsonResult upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if (!file.isEmpty()) {
            String contentType = file.getContentType();
            String fileName = file.getOriginalFilename();
            String filePath = location;

            try {
                FileUtils.uploadFile(file.getBytes(), filePath, fileName);
                return JsonResult.success("上传成功," + fileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return JsonResult.failure("上传失败," + e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                return JsonResult.failure("上传失败," + e.getMessage());
            }
        } else {
            return JsonResult.failure("上传失败，因为文件为空.");
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
