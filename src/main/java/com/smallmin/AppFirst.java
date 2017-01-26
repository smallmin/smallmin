package com.smallmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.smallmin.Enity.Tag;
import com.smallmin.Service.TagService;

/** 
* @author jmlu
* @version 2017年1月26日 下午6:46:28 
*  
*/
@Component
public class AppFirst implements CommandLineRunner  {
	
	@Autowired
	TagService tagService;
	
	public void run(String... args) throws Exception {
		System.out.println(">>>>>>>>>>>>> Data initialize >>>>>>>>>>>>>");
		System.out.println(">>>>>>>>>>>>> load tags");
		for(Tag tag : tagService.getAll()){
			App.tags.put(tag.getId(), tag);
		}
	}

}
