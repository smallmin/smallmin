package com.smallmin.Tool;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.exception.AbstractCosException;
import com.qcloud.cos.sign.Credentials;
import com.qcloud.cos.sign.Sign;
import com.smallmin.AppConfig;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class TencentCosTools {
	
	  
	
	public static String getAuthorization(int timeRange) {
		/*Random random = new Random();
		long currentTimestamp = new Date().getTime()/1000;
		long invalidTimestamp = currentTimestamp + timeRange;
		long randomParameter = random.nextInt();
		
		String origin = "a="  + AppConfig.appId +
				        "&b=" + AppConfig.bucket +
				        "&k=" + AppConfig.secretId +
				        "&e=" + String.valueOf(invalidTimestamp) +
				        "&t=" + String.valueOf(currentTimestamp) +
				        "&r=" + String.valueOf(randomParameter) + 
				        "&f=";
		
		String authorization = "";
		
		try {
			byte[] key_data = AppConfig.secretKey.getBytes("UTF-8");
			byte[] origin_data = origin.getBytes("UTF-8");
			SecretKey secretKey = new SecretKeySpec(key_data, "HmacSHA1");
			Mac mac = Mac.getInstance("HmacSHA1");
			mac.init(secretKey);
			byte[] hash_data=mac.doFinal(origin_data);
			byte[] last = new byte[hash_data.length+origin_data.length];
			System.arraycopy(hash_data,0,last,0,hash_data.length);
			System.arraycopy(origin_data,0,last,hash_data.length,origin_data.length);
			authorization = encodeBase64(last);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return authorization;
		*/
		
		// 初始化秘钥信息
        Credentials cred = new Credentials(AppConfig.appId, AppConfig.secretId, AppConfig.secretKey);
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setRegion("sh");
        COSClient cosClient = new COSClient(clientConfig, cred);
        long expired = System.currentTimeMillis() / 1000 + 36000;
        String signStr = "";
        try {
			signStr = Sign.getPeriodEffectiveSign(AppConfig.bucket, "/", cred, expired);
		} catch (AbstractCosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return signStr;
	}
	
	
	public static String test() {
		
		
        // 初始化秘钥信息
        Credentials cred = new Credentials(AppConfig.appId, AppConfig.secretId, AppConfig.secretKey);
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setRegion("sh");
        COSClient cosClient = new COSClient(clientConfig, cred);
        long expired = System.currentTimeMillis() / 1000 + 36000;
        String signStr = "";
        try {
			signStr = Sign.getPeriodEffectiveSign(AppConfig.bucket, "/", cred, expired);
		} catch (AbstractCosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return signStr;
	}
}
