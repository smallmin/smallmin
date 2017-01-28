package com.smallmin;
/** 
* @author jmlu
* @version 2017年1月26日 下午3:41:55 
*  
*/
public class AppConfig {
	//移动数据文件夹时，只要修改此处的appDataFolder的路径即可。
	public static String appDataFolder = "C:/smallminData/";
	
	//以下为相应数据子目录
	public static String topicContentPath=appDataFolder+"topic/content/"; //帖子内容
	public static String topicIntroductionPath=appDataFolder+"topic/introduction/"; //帖子介绍
	public static String imageContentPath=appDataFolder+"img/"; //图片
	
	
}
