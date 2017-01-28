package com.smallmin.Api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.smallmin.AppConfig;
import com.smallmin.Enity.Topic;
import com.smallmin.Service.TopicService;
import com.smallmin.Tool.CreateUnicodeFile;

/** 
* @author jmlu
* @version 2017年1月28日 上午1:18:44 
*  
*/
@RestController
public class TopicApi {
	
	@Autowired
	TopicService topicService;
	
	
	@RequestMapping(value="/api/topic")
	public Collection<Topic> getAll(){
		return topicService.getAll();
	}
	
}
