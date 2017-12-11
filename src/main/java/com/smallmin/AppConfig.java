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
	
	
	//qcloud 相关参数
	public static long appId = 1252380644;
	public static String secretId = "AKIDHTVYnHRjfmyEMJ87ZTDuSjBwmW4S4I5X";
	public static String secretKey = "S1kYck9RYUiojW73qS8xw2zoVBlAeEv8";
	public static String host = "sh.file.myqcloud.com";
	public static String bucket = "test";
	
	//管理员帐号密码
	public static String adminUser = "jimlu";
	public static String adminPass = "zxcljm???123ws";
	
	// 超时时间为 30 秒
	public static int outTime = 10;
}
