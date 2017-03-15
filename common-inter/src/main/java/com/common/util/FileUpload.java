package com.common.util;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;
/**
 * 文件上传下载工具类
 * @author lwj
 *
 */
public class FileUpload {
	/*** 
     * 保存文件 
     * @param file,int Len
     * @return 
     */
	public String saveFile(MultipartFile file,int Len) {  
		RandomNameUtil randomNameUtil=new RandomNameUtil();
		String RandomName=randomNameUtil.getRandomName(Len);
		// 判断文件是否为空  
        if (!file.isEmpty()) {  
            try {  
                // 文件保存路径
            	String[] Attributes=file.getOriginalFilename().split("[.]");
                String filePath="D:\\"+RandomName+"."+Attributes[Attributes.length-1]; 
                // 转存文件  
                file.transferTo(new File(filePath));
            } catch (Exception e) {  
                e.printStackTrace();  
            }
        }  
        return RandomName;  
    }  
}
