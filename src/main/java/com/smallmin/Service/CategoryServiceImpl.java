package com.smallmin.Service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smallmin.Dao.CategotyDao;
import com.smallmin.Enity.Category;

/** 
* @author jmlu
* @version 2017年1月29日 下午9:38:39 
*  
*/
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategotyDao categotyDao;
	
	public Collection<Category> getAll() {
		return (Collection<Category>) categotyDao.findAll();
	}

	public Category getOne(int id) {
		return categotyDao.findOne(id);
	}

	public Category update(Category category) {
		return categotyDao.save(category);
	}

	public Category add(Category category) {
		return categotyDao.save(category);
	}

	public void delete(int id) {
		categotyDao.delete(id);
		
	}

}
