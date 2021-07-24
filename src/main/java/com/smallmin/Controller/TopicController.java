package com.smallmin.Controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import static java.lang.Math.*;

import com.smallmin.Api.TopicApi;
import com.smallmin.Tool.CosTools;  



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
	
	@RequestMapping(value="/preview", method = RequestMethod.GET)
	public String getPreview(HttpServletRequest requset, Model model){
		TopicApi topicApi = new TopicApi();
		
		String[] fileList = topicApi.getContentIds();
		int maxId = 1;
		for(String f : fileList) {
			maxId = max(maxId, Integer.valueOf(f));
		}
		model.addAttribute("content", topicApi.getContentById(maxId));

		return "preview";
	}

	@RequestMapping(value="/preview/{id}", method = RequestMethod.GET)
	public String getPreview(HttpServletRequest requset, Model model,@PathVariable("id") int id){
		TopicApi topicApi = new TopicApi();
		model.addAttribute("content", topicApi.getContentById(id));
		return "preview";
	}
	
	@RequestMapping(value="/testCOS", method = RequestMethod.GET)
	public String getTestCOS(HttpServletRequest requset, HttpServletResponse response, Model model){
		try {
			model.addAttribute("auth", CosTools.getAuthorization(3000));
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.addCookie(new Cookie("testCookie", "wahaha"));
		return "sub/index";
	}
	
	
}
