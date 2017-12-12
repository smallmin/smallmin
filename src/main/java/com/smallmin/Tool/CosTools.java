package com.smallmin.Tool;

import com.qcloud.cos.sign.Credentials;
import com.qcloud.cos.sign.Sign;
import com.smallmin.AppConfig;

public class CosTools {
	
	public static String getAuthorization(int timeRange) throws Exception {
		// 根据时间获取类
        Credentials cred = new Credentials(AppConfig.appId, AppConfig.secretId, AppConfig.secretKey);
        long expired = System.currentTimeMillis() / 1000 + timeRange;
        String signStr = Sign.getPeriodEffectiveSign(AppConfig.bucket, "/", cred, expired);
        return signStr;
	}
	  
}
