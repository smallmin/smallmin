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
		try {
			// 获取临时标记用于登陆,加密时用标记置乱,并标记登陆发起IP及时间
			// 防止每次登录MD5码相同,标记有效10秒,且用于认证IP
			String addr = requset.getRemoteAddr();
			String validTime = String.valueOf(new Date().getTime()/1000+AppConfig.outTime);
			// 构造 info map
			Map<String,String> tempSign = new HashMap<String, String>();
			tempSign.put("validTime", validTime);
			tempSign.put("addr", addr);
			// 根据 info map 加密得到 token
			String tokenStr = TokenTool.getTokenByMap(tempSign);
			// 加入 cookie
			response.addCookie(new Cookie("tempSign",tokenStr));
			return tokenStr;
		}
		catch (Exception e) {
			// 有非法情况发生,需加到日志,待完成
			return "failed by error, please concat to admin";
		}
	}
	
	@RequestMapping(value="/user/login", method = RequestMethod.POST)
	@ResponseBody
	public String getTestCOS(@RequestParam("postPass") String postPass, HttpServletRequest requset, HttpServletResponse response, Model model){
		try {
			// POST 根据tempSign username password 生成好的MD5码,来验证登陆
			// tempSign在cookie中，用于验证IP和时间
			Cookie[] cookies = requset.getCookies();
			String tokenStr = null;
			for (Cookie c:cookies){
				if(c.getName().equals("tempSign")) {
					tokenStr = c.getValue();
					break;
				}
			}
			// 找不到tempSign,提示重试
			if(tokenStr==null) return "failed by tempSign missing, please press F5 to fresh & try again";
			// 根据 tempSign 解密得到 info map
			Map tempSign = TokenTool.getMapByToken(tokenStr);
			// 验证时间有效期，
			long validTime = Long.valueOf((String) tempSign.get("validTime"));
			long  currentTime = new Date().getTime()/1000;
			if(currentTime>validTime) return "failed by time out (10 seconds), please try again";
			// 如果IP改变，提示重试
			String addr = (String) tempSign.get("addr");
			if(!addr.equals(requset.getRemoteAddr())) return "failed of IP changed, please try again";
			String truePass= TokenTool.getMD5(tokenStr+AppConfig.adminUser+TokenTool.getMD5(AppConfig.adminPass));
			
			Map<String,String> userSign = new HashMap<String, String>();
			userSign.put("addr", addr);
			userSign.put("role", "admin");
			userSign.put("validTime", String.valueOf(new Date().getTime()/1000+21600));
			
			String userToken = TokenTool.getTokenByMap(userSign);
			response.addCookie(new Cookie("userToken", userToken));
			System.out.println(userToken);
			return userToken;
		}
		catch (Exception e) {
			// 有非法情况发生,需加到日志,待完成
			return "failed by error, please concat to admin";
		}
	}
	
	
}
