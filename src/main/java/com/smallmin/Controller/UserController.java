package com.smallmin.Controller;

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
import com.smallmin.Tool.CosTools;
import com.smallmin.Tool.TokenTool;

/** 
* @author Jimlu
* @version 2017年12月10日 下午4:24:29 
* 用户相关接口,目前主要用于管理员验证
*/

@Controller
public class UserController {
	
	@RequestMapping(value="/user/tempsign", method = RequestMethod.GET)
	@ResponseBody
	public String getTempSign(HttpServletRequest requset,HttpServletResponse response){
		try {
			// 获取临时标记用于登陆,加密时用以标记置乱,并标记登陆发起的IP及时间
			// 作用：防止每次登录MD5码相同,拥有时效性，且保证了IP相同，有效时长为AppConfig.outTime
			String addr = requset.getRemoteAddr();
			String validTime = String.valueOf(System.currentTimeMillis()/1000+AppConfig.outTime);
			// 构造 info map, 记录IP和合法时间
			Map<String,String> tempSign = new HashMap<String, String>();
			tempSign.put("validTime", validTime);
			tempSign.put("addr", addr);
			// 根据 info map 加密得到 token
			String tokenStr = TokenTool.getTokenByMap(tempSign);
			// 加入 cookie
			Cookie cookie = new Cookie("tempSign", tokenStr);
			
			cookie.setMaxAge(AppConfig.outTime);
			response.addCookie(cookie);
			return tokenStr;
		}
		catch (Exception e) {
			// 有非法情况发生,需加到日志,待完成
			return "failed by error, please concat to admin";
		}
	}
	
	@RequestMapping(value="/user/login", method = RequestMethod.POST)
	@ResponseBody
	public String userLogin(@RequestParam("postPass") String postPass,
							 @RequestParam("username") String username,
							 HttpServletRequest requset,
							 HttpServletResponse response){
		try {
			
			System.out.println("username: " + username);
			// 必须含cookie: tempSign
			String tempSignStr = getCookieValue("tempSign", requset);
			if(tempSignStr==null)
				return "failed by tempSign missing, please press F5 to fresh and try again";
			
			System.out.println(tempSignStr);
			// 解密token得到info map
			Map tempSign = TokenTool.getMapByToken(tempSignStr); 
			System.out.println(">>ok");
			
			// 验证info map的地址和时间的合法性
			if(!checkTime(tempSign))
				return "failed by time out, please try again";
			if(!checkAddr(tempSign, requset.getRemoteAddr()))
				return "Ip changed, please press F5 to fresh and try again";
			
			// 验证帐号密码正确性
			String realPass = getRealPass(username, tempSignStr);
			if(!realPass.equals(postPass))
				return "username or password wrong, please try again " + realPass;
			
			// 构造info Map
			Map<String,String> userSign = new HashMap<String, String>();
			userSign.put("addr", requset.getRemoteAddr());
			userSign.put("username", username);
			userSign.put("validTime", String.valueOf(System.currentTimeMillis()/1000+AppConfig.loginValidTime));
			String userSignToken = TokenTool.getTokenByMap(userSign);
			response.addCookie(new Cookie("userSign", userSignToken));
			
			return userSignToken;
		}
		catch (Exception e) {
			// 有非法情况发生,需加到日志,待完成
			System.out.println(e.getMessage());
			return "failed by error, please concat to admin";
		}
	}
	
	@RequestMapping(value="/user/cossign", method = RequestMethod.GET)
	@ResponseBody
	public String getCosSign(HttpServletRequest requset, HttpServletResponse response){
		try {
			// 必须含cookie: userSign
			String userSignStr = getCookieValue("userSign", requset);
			if(userSignStr==null)
				return "please login first!";
			
			// 解密token得到info map
			Map userSign = TokenTool.getMapByToken(userSignStr);
			
			// 验证info map的地址和时间的合法性
			if(!checkTime(userSign))
				return "failed by time out, please try again";
			if(!checkAddr(userSign, requset.getRemoteAddr()))
				return "Ip changed, please press F5 to fresh and try again";
			
			System.out.println(">>>"+userSign.get("addr")+" "+requset.getRemoteAddr());
			// 验证帐号权限
			if(!userSign.get("username").equals(AppConfig.adminUser))
				return "pleas login with admin accout!";
			
			// 返回一个有效期为AppConfig.outTime的cossign
			return CosTools.getAuthorization(AppConfig.outTime);
		}
		catch (Exception e) {
			// 有非法情况发生,需加到日志,待完成
			return "failed by error, please concat to admin";
		}
	}
	
	@RequestMapping(value="/user", method = RequestMethod.GET)
	public String getTestCOS(HttpServletRequest requset, HttpServletResponse response, Model model){
		return "user";
	}
	
	private String getCookieValue(final String cookieName, final HttpServletRequest request) {
		// get cookie value from request by name
		Cookie[] cookies = request.getCookies();
		for (Cookie c:cookies)
			if(c.getName().equals(cookieName)) 
				return c.getValue();
		return null;
	}
	
	private boolean checkTime(final Map infoMap) {
		// validate time in info map
		String validTimeStr = (String) infoMap.get("validTime");
		long validTime=0, currentTime = System.currentTimeMillis()/1000;
		if(validTimeStr!=null) validTime = Long.valueOf(validTimeStr);
		if(currentTime<=validTime)return true;
		return false;	
	}
	
	private boolean checkAddr(final Map infoMap, final String remoteAddr) {
		// validate address in info map
		String validAddr = (String) infoMap.get("addr");
		if(validAddr.equals(remoteAddr))return true;
		return false;	
	}
	
	private String getRealPass(final String username, final String tempSignStr) throws Exception {
		// 根据tempSign和username生成realPass
		// 用户暂时只有admin, 没有数据库辅助
		String userPasswordMD5HexStr = "userPassword_MD5_HexStr";
		if(username.equals(AppConfig.adminUser)) userPasswordMD5HexStr = TokenTool.getMD5HexStr(AppConfig.adminPass);
		return TokenTool.getMD5HexStr(tempSignStr + username + userPasswordMD5HexStr);
	}
	
}
