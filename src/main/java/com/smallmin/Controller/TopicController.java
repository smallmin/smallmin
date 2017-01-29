package com.smallmin.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.smallmin.Api.TopicApi;

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
		model.addAttribute("content", topicApi.test(1));
		return "preview";
	}
	
}
