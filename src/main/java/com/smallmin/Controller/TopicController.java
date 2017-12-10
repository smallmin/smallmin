package com.smallmin.Controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smallmin.Api.TopicApi;
import com.smallmin.Tool.TencentCosTools;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;  



/** 
* @author jmlu
* @version 2017年1月28日 下午6:35:21 
*  
*/
@Controller
public class TopicController {
	
	
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String test(HttpServletRequest requset, Model model){
		return "index";
	}
	
	@RequestMapping(value="/editor", method = RequestMethod.GET)
	public String editor(HttpServletRequest requset, Model model){
		return "editor";
	}
	@RequestMapping(value="/preview/{id}", method = RequestMethod.GET)
	public String getPreview(HttpServletRequest requset, Model model,@PathVariable("id") int id){
		TopicApi topicApi = new TopicApi();
		model.addAttribute("content", topicApi.getContentById(id));
		return "preview";
	}
	
	@RequestMapping(value="/testCOS", method = RequestMethod.GET)
	public String getTestCOS(HttpServletRequest requset, HttpServletResponse response, Model model){
		model.addAttribute("auth", TencentCosTools.getAuthorization(3000));
		response.addCookie(new Cookie("testCookie", "wahaha"));
		return "testCOS";
	}
}
