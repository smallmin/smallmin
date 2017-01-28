package com.smallmin.Api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smallmin.Service.TagService;
import com.smallmin.App;
import com.smallmin.Enity.Tag;

/** 
* @author jmlu
* @version 2017年1月26日 下午7:02:36 
*  
*/
@RestController
public class TagApi {
	
	@Autowired
	TagService tagService;
	
	@RequestMapping(value = "/api/tag")
	public Collection<Tag> getAll() {
		return App.tags.values();
	}
	
	@RequestMapping(value = "/api/tag/size")
	public int getSize() {
		return App.tags.size();
	}
	
	@RequestMapping(value = "/api/tag/{id}")
	public Tag getOne(@PathVariable("id") int id) {
		return App.tags.get(id);
	}
	
	@RequestMapping(value = "/api/tag" ,method = RequestMethod.PUT)
	public Tag update(@RequestBody Tag tag) {
		App.tags.put(tag.getId(), tag);
		return tagService.update(tag);
	}
	
	@RequestMapping(value = "/api/tag" ,method = RequestMethod.POST)
	public Tag add(@RequestBody Tag tag) {
		Tag temp = tagService.add(tag); //此处需要temp，以防request带了id
		App.tags.put(temp.getId(), temp);
		return temp;
	}
	
	@RequestMapping(value = "/api/tag/{id}" ,method = RequestMethod.DELETE)
	public void add(@PathVariable("id") int id) {
		App.tags.remove(id);
		tagService.delete(id);
	}
	
	
}
