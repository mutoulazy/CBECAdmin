package net.sppan.base.common.utils;

import java.io.*;

/**
 * 文件工具类
 * Created by mutoulazy on 2018/4/9.
 */
public class FileUtils {
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
