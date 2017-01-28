package com.smallmin.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/** 
* @author jmlu
* @version 2017年1月28日 下午6:35:21 
*  
*/
@Controller
public class HelloController {
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String test(HttpServletRequest requset, Model model){
		return "index";
	}
}
