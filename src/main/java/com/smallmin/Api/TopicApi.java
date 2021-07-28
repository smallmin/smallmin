package com.smallmin.Api;


import java.util.Collection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smallmin.AppConfig;
import com.smallmin.Enity.Topic;
import com.smallmin.Service.TopicService;
import com.smallmin.Tool.FileTool;

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

	
	
	@RequestMapping(value="/api/topic", method = RequestMethod.POST)
	public Topic test(HttpServletRequest requset){
		Topic topic = new Topic();
		topic.setEditTime(new Date());
		topic.setPriority(0);
		topic.setTitle(requset.getParameter("title"));
		topic.setAuthorId(1);
		topic.setCategoryId(0);
		topic.setIntroduction(requset.getParameter("introduction"));
		topic = topicService.add(topic);

		if(FileTool.createFile(AppConfig.topicContentPath, requset.getParameter("content"), String.valueOf(topic.getId())))
			System.out.println(">>>>>>>>>>>>>>>>>>>> 文件 " +String.valueOf(topic.getId())+ " 创建成功");
		return topic;
	}
	
	@RequestMapping(value="/api/topic/content/{id}")
	public String getContentById(@PathVariable("id") int id){
		return FileTool.readFile(AppConfig.topicContentPath, String.valueOf(id));
	}

	@RequestMapping(value="/api/topic/content/ids")
	public String[] getContentIds(){
		return FileTool.getFileList(AppConfig.topicContentPath);
	}
}
