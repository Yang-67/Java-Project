package com.example.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Upload {
    public List<String> upload_doc(List<FileItem> fileItems, String uploadPath){
        List<String> doc_names = new ArrayList<>();
        String filename=null;
        for(FileItem fileItem : fileItems){
            if(!fileItem.isFormField()){//处理上传表单项
                System.out.println(uploadPath);
                File dir = new File(uploadPath);
                if(!dir.exists()){
                    dir.mkdirs();
                }
                //获取上传文件的全路径
                filename = fileItem.getName();
                System.out.println(filename);
                if(filename != null){
                    filename = FilenameUtils.getName(filename);//文件名称，不带路径
                }
                filename = UUID.randomUUID()+"_"+filename;
                try {
                    doc_names.add(filename);
                    fileItem.write(new File(dir,filename));
                } catch (Exception e) {
                    e.printStackTrace();
                    return doc_names;
                }
            }
        }
        return doc_names;
    }

}
