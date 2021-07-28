package com.smallmin.Controller;

import static java.lang.Math.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.smallmin.Api.TopicApi;
import com.smallmin.Enity.Topic;
import com.smallmin.Service.TopicService;
import com.smallmin.Tool.CosTools;  

/** 
* @author jmlu
* @version 2017年1月28日 下午6:35:21 
*/

@Controller
public class TopicController {
	
	@Autowired
	TopicService topicService;
	
	public String getTopicResultHtml(List<Topic> topics) {
		String html = new String();
		for(Topic topic: topics) {
			html += String.format(
				"<div class='sheet' href='/editor')><div class='title'><div class='square'></div><a href = '/preview/%d'>%s</a></div><div><a href = '/preview/%d'>%s</a></div></div>"
			, topic.getId(), topic.getTitle(), topic.getId(), topic.getIntroduction());
		}
		return html;
	}

	public String getTopicPageSelectHtml(List<Topic> topics, Integer activeIndex) {
		int len = topics.size();
		int pages = (len + 9) / 10;

		String html = "<div style='text-align: center;'>"
		+"<ul class='pagination'>"
		+"<li><a href='/page/1'>&laquo;</a></li>";

		for (int i = 1; i <= pages; i ++) {
			if (i == activeIndex) {
				html += "<li class='active'><a href='/page/" + i + "'>" + i + "</a></li>";
			} else {
				html += "<li><a href='/page/" + i + "'>" + i + "</a></li>";
			}
		}

		html +=
		"<li><a href='/page/" + pages + "'>&raquo;</a></li>"
		+"</ul>"
		+"</div>";
		return html;
	}

	public String getPageHtml(Model model, Integer pageId, String key) {
		String html = new String();

		List<Topic> totalTopic = (List<Topic>) topicService.getAll();
		List<Topic> pageTopics = new ArrayList<Topic>();

		if (key != null) {
			List<Topic> filteTopics = new ArrayList<Topic>();
			for(Topic topic: totalTopic){
				System.out.println(topic.getTitle() + key + " " + topic.getTitle().indexOf(key));
				if(topic.getTitle().indexOf(key) >= 0) {
					filteTopics.add(topic);	
				}
			}
			totalTopic = filteTopics;

			model.addAttribute("searchKey", "<h1>" + key + " 的搜索结果：</h1>");
		}


		for (int i = (pageId - 1) * 10; i <= min(totalTopic.size()-1, pageId*10-1); i++) {
			pageTopics.add(totalTopic.get(totalTopic.size()-i-1));
		}
		
		model.addAttribute("pageTopics", getTopicResultHtml(pageTopics));
		model.addAttribute("pageSelect", getTopicPageSelectHtml(totalTopic, pageId));
		return html;
	}


	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String  getSearchResult(HttpServletRequest requset, Model model, @RequestParam("key") String key, @RequestParam(name="page", required = false) Integer page){
		if (page == null) page = 1;
		getPageHtml(model, page, key);
		return "index";
	}

	@RequestMapping(value="/page/{id}", method = RequestMethod.GET)
	public String getPage(HttpServletRequest requset, Model model, @PathVariable("id") int id){
		getPageHtml(model, id, null);
		return "index";
	}

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String test(HttpServletRequest requset, Model model){
		getPageHtml(model, 1, null);
		return "index";
	}
	
	@RequestMapping(value="/editor", method = RequestMethod.GET)
	public String editor(HttpServletRequest requset, Model model){
		return "editor";
	}
	
	@RequestMapping(value="/preview", method = RequestMethod.GET)
	public String getPreview(HttpServletRequest requset, Model model){
		TopicApi topicApi = new TopicApi();
		List<Topic> totalTopic = (List<Topic>) topicService.getAll();
		Integer maxId = totalTopic.size();
		for (Topic topic : totalTopic) {
			if (topic.getId() == maxId) {
				model.addAttribute("title", topic.getTitle()); 
				model.addAttribute("introduction", topic.getIntroduction()); 
			}
		}
		model.addAttribute("content", topicApi.getContentById(maxId)); 
		
		return "preview";
	}

	@RequestMapping(value="/preview/{id}", method = RequestMethod.GET)
	public String getPreview(HttpServletRequest requset, Model model,@PathVariable("id") int id){
		TopicApi topicApi = new TopicApi();
		Topic topic = topicService.getOne(id);
		model.addAttribute("title", topic.getTitle()); 
		model.addAttribute("introduction", topic.getIntroduction()); 
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
