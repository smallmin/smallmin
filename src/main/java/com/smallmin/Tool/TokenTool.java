package com.smallmin.Tool;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.Map;

import org.hibernate.sql.ordering.antlr.GeneratedOrderByFragmentRendererTokenTypes;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/** 
* @author Jimlu
* @version 2017年12月10日 下午5:16:30 
* 
*/
public class TokenTool {
	
	public static String getJsonByObject(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			json = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}  
		return json;
	}
	
	public static Map getMapByJson(String jsonStr) {
		ObjectMapper mapper = new ObjectMapper();
		Map map=null;
		try {
			map = mapper.readValue(jsonStr, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public static String getToken(Object obj) {
		String jsonStr = getJsonByObject(obj);
		String tokenStr = null;
		try {
			tokenStr=TencentCosTools.encodeBase64(jsonStr.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tokenStr;
	}
	
	public static Map getMapByToken(String tokenStr) {
		Map map=null;
		try {
			String jsonStr = new String(TencentCosTools.decodeBase64(tokenStr));
			map = getMapByJson(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
	public static String getMD5(String s) {
	    try {
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        byte[] bytes = md.digest(s.getBytes("utf-8"));
	        return byteArrayToHex(bytes);
	    }
	    catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

	public static String byteArrayToHex(byte[] bytes) {
	    final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
	    StringBuilder ret = new StringBuilder(bytes.length * 2);
	    for (int i=0; i<bytes.length; i++) {
	        ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
	        ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
	    }
	    return ret.toString();
	}
}
