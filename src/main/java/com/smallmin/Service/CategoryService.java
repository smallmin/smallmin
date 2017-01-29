package com.smallmin.Service;
/** 
* @author jmlu
* @version 2017年1月29日 下午9:35:57 
*  
*/

import java.util.Collection;

import com.smallmin.Enity.Category;
public interface CategoryService{
	public Collection<Category> getAll();
	public Category getOne(int id);
	public Category update(Category category);
	public Category add(Category category);
	public void delete(int id);
}
