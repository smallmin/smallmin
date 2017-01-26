package com.smallmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.smallmin.Tool.CreateUnicodeFile;

/**
 * Hello world!
 *
 */
//@SpringBootApplication
public class App
{
    public static void main( String[] args ) throws Exception
    {
    	String str=CreateUnicodeFile.createFile(AppConfig.topicContentPath,"12345");
    	if(str!=null){
    		System.out.println(str);
    	}
    	else System.out.println("写入失败");
    	
    	//SpringApplication.run(App.class, args);
    }
}
