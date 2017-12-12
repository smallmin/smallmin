package com.smallmin.Tool;

import java.io.IOException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Map;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.hibernate.sql.ordering.antlr.GeneratedOrderByFragmentRendererTokenTypes;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smallmin.AppConfig;


/**  
* @author Jimlu
* @version 2017年12月10日 下午5:16:30 
* 
*/
public class TokenTool {
	
	static public String getUUID(){
		return  java.util.UUID.randomUUID().toString().replaceAll("-", ""); 
	}
	
	public static String encodeBase64(byte[]input) throws Exception{  
	    Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");  
	    Method mainMethod= clazz.getMethod("encode", byte[].class);  
	    mainMethod.setAccessible(true);  
	    Object retObj=mainMethod.invoke(null, new Object[]{input});  
	    return (String)retObj;  
	}
	
	public static byte[] decodeBase64(String input) throws Exception{  
        Class clazz=Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");  
        Method mainMethod= clazz.getMethod("decode", String.class);  
        mainMethod.setAccessible(true);  
        Object retObj=mainMethod.invoke(null, input);  
        return (byte[])retObj;  
    }
	
	public static String getJsonByMap(Map<String,String> map) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Random rdm = new Random();
		map.put("rdmSalt", String.valueOf(rdm.nextInt()));
		return mapper.writeValueAsString(map);
	}
	
	public static Map getMapByJson(String jsonStr) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(jsonStr, Map.class);
	}
	
	private static Cipher getAESCipher(byte[] passwordBytes,int MODE) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");  
        kgen.init(128, new SecureRandom(passwordBytes));  
        SecretKey secretKey = kgen.generateKey();  
        byte[] enCodeFormat = secretKey.getEncoded();  
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(MODE, key);
        return cipher;
	}
	
	public static byte[] AES_encrypt(byte[] originBytes) throws Exception {            
    	Cipher cipher = getAESCipher(AppConfig.secretKey.getBytes("utf-8"),Cipher.ENCRYPT_MODE);
        return cipher.doFinal(originBytes);
	}  
	
	public static byte[] AES_decrypt(byte[] cryptBytes) throws Exception {  
    	Cipher cipher = getAESCipher(AppConfig.secretKey.getBytes("utf-8"),Cipher.DECRYPT_MODE);             
        return cipher.doFinal(cryptBytes);   
	}
	
	public static String getTokenByMap(Map<String, String> map) throws Exception {
		String jsonStr = getJsonByMap(map);
		byte[] cryptBytes = AES_encrypt(jsonStr.getBytes("utf-8"));
		return encodeBase64(cryptBytes);
	}
	
	public static Map getMapByToken(String tokenStr) throws Exception {
		byte[] originBytes = AES_decrypt(decodeBase64(tokenStr));
		String jsonStr = new String(originBytes,"utf-8");
		return getMapByJson(jsonStr);
	}
	
	public static String getMD5HexStr(String orginStr) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] bytes = md.digest(orginStr.getBytes("utf-8"));
        return byteArrayToHex(bytes);
	}
	
	public static String byteArrayToHex(byte[] bytes) throws Exception {
	    final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
	    StringBuilder ret = new StringBuilder(bytes.length * 2);
	    for (int i=0; i<bytes.length; i++) {
	        ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
	        ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
	    }
	    return ret.toString();
	}
	
}
