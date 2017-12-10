package com.smallmin.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smallmin.AppConfig;
import com.smallmin.Tool.FileTool;
import com.smallmin.Tool.TokenTool;

/** 
* @author Jimlu
* @version 2017年12月10日 下午4:24:29 
* 用户相关接口,目前主要用于管理员验证
*/

@Controller
public class UserController {
	
	
	@RequestMapping(value="/user/getkey", method = RequestMethod.GET)
	@ResponseBody
	public String getUserKey(HttpServletRequest requset,HttpServletResponse response){
		String addr = requset.getRemoteAddr();
		String validTime = String.valueOf(new Date().getTime()/1000+30);
		Map<String,String> tempSign = new HashMap<String, String>();
		tempSign.put("validTime", validTime);
		tempSign.put("addr", addr);
		String tokenStr = TokenTool.getToken(tempSign);
		response.addCookie(new Cookie("tempSign",tokenStr));
		return tokenStr;
	}
	
	
	@RequestMapping(value="/user/login", method = RequestMethod.POST)
	@ResponseBody
	public String getTestCOS(@RequestParam("postPass") String postPass, HttpServletRequest requset, HttpServletResponse response, Model model){
		Cookie[] cookies = requset.getCookies();
		String tokenStr = null;
		for (Cookie c:cookies){
			if(c.getName().equals("tempSign")) {
				tokenStr = c.getValue();
				break;
			}
		}
		if(tokenStr==null) return "failed of token miss, please try again";
		
		Map tempSign = TokenTool.getMapByToken(tokenStr);
		if(tempSign==null) return "failed of token unvalid, please try again";
		
		String addr = (String) tempSign.get("addr");
		
		long validTime = 0;
		long  currentTime = new Date().getTime()/1000;
		try {
			validTime = Long.valueOf((String) tempSign.get("validTime"));
		}
		catch (Exception e) {
			return "failed of token unvalid, please try again";
		}
		
		if(currentTime>validTime) return "failed of time out, please try again";
		if(!addr.equals(requset.getRemoteAddr())) return "failed of IP changed, please try again";
		
		String truePass = TokenTool.getMD5(tokenStr+AppConfig.adminUser+TokenTool.getMD5(AppConfig.adminPass));
		
		
		Map<String,String> userSign = new HashMap<String, String>();
		userSign.put("addr", addr);
		userSign.put("role", "admin");
		userSign.put("validTime", String.valueOf(new Date().getTime()/1000+21600));
		
		String userToken = TokenTool.getToken(userSign);
		response.addCookie(new Cookie("userToken", userToken));
		return postPass+" " +truePass+" "+userToken;
	}
	
	
}
