package com.smallmin.Api;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** 
* @author jmlu
* @version 2017年1月25日 下午4:52:59 
*  
*/

@RestController
public class Test {
	
	@RequestMapping(value="/")
	public String getIndex(){
		return "你好";
	}
}
