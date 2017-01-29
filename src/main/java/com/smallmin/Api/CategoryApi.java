package com.smallmin.Api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smallmin.App;
import com.smallmin.Enity.Category;
import com.smallmin.Service.CategoryService;

/** 
* @author jmlu
* @version 2017年1月29日 下午9:43:16 
*  
*/

@RestController
public class CategoryApi {
	
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping(value = "/api/category")
	public Collection<Category> getAll() {
		return App.categorys.values();
	}
	
	@RequestMapping(value = "/api/category/size")
	public int getSize() {
		return App.categorys.size();
	}
	
	@RequestMapping(value = "/api/category/{id}")
	public Category getOne(@PathVariable("id") int id) {
		return App.categorys.get(id);
	}
	
	@RequestMapping(value = "/api/category" ,method = RequestMethod.PUT)
	public Category update(@RequestBody Category category) {
		App.categorys.put(category.getId(), category);
		return categoryService.update(category);
	}
	
	@RequestMapping(value = "/api/category" ,method = RequestMethod.POST)
	public Category add(@RequestBody Category category) {
		Category temp = categoryService.add(category); //此处需要temp，以防request带了id
		App.categorys.put(temp.getId(), temp);
		return temp;
	}
	
	@RequestMapping(value = "/api/category/{id}" ,method = RequestMethod.DELETE)
	public void add(@PathVariable("id") int id) {
		App.categorys.remove(id);
		categoryService.delete(id);
	}
	
}
