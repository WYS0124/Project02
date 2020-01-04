package cn.kgc.util;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUploadUtil {

    public static final String savePosition="F:\\ProjectPictureFile\\";

    public static String upload(CommonsMultipartFile pfile){
        try {
            //获取文件的扩展名
            String fname = pfile.getOriginalFilename();
            String fexpName = fname.substring(fname.lastIndexOf("."));
            //生成新的文件名
            String unique = System.currentTimeMillis() + "";
            String saveFileName = unique + fexpName;
            String savePath =  savePosition + saveFileName;
            File savefile = new File(savePath);
            pfile.transferTo(savefile); //上传

            return saveFileName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean deleteFile(String fileName){
        File file = new File(savePosition+fileName);
        return file.delete();
    }
}
