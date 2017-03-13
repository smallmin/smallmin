package com.smallmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.smallmin.Enity.*;
import com.smallmin.Service.*;

/** 
* @author jmlu
* @version 2017年1月26日 下午6:46:28 
*  
*/
@Component
public class AppFirst implements CommandLineRunner  {
	
	@Autowired
	TagService tagService;
	
	@Autowired
	CategoryService categoryService;
	
	public void run(String... args) throws Exception {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> Data initialize: >>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> load tags");
		for(Tag tag : tagService.getAll()){
			App.tags.put(tag.getId(), tag);
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> Successful");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> load categorys");
		System.out.println(categoryService.getAll().size());
		for(Category category : categoryService.getAll()){
			App.categorys.put(category.getId(), category);
		}
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> Successful");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>> Initialize ended. >>>>>>>>>>>>>>>>>>>>>>>>>");
	}

}
