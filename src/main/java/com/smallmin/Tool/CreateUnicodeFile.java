package com.smallmin.Tool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/** 
* @author jmlu
* @version 2017年1月26日 下午3:45:20 
*  
*/
public class CreateUnicodeFile {
	
	static private String getUUID(){
		//用唯一识别码作为文件名
		return  java.util.UUID.randomUUID().toString().replaceAll("-", ""); 
	}
	
	public static File createFile(String path,String name){
		//如果文件不存在，创建相应的目录和文件，返回文件，失败返回null
		File file = new File(path+name);
		if(!file.getParentFile().exists()) {  
			//如果父目录不存在，先创建目录
		    if(!file.getParentFile().mkdirs()) {  
		        System.out.println("Error : 创建目录 " + file.getParentFile() + " 失败");  
		        return null;  
		    }  
		}  
		try {
			file.createNewFile();
			return file;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	public static boolean overlapFile(File file,String content){
		//写入文件内容，true成功
		if(file==null)return false;
		try {
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(content.getBytes());
			return true;
		} catch (Exception  e) {
			e.printStackTrace();
		}
		return false;
	}
	public static String createFile(String path,String content,String name){
		//创建文件的同时，写入文件内容，返回文件名，若为null说明失败
		File file=createFile(path,name);
		if(overlapFile(file,content)){
			System.out.println("!!!");
			return file.getName();
		}
		return null;
	}
}
