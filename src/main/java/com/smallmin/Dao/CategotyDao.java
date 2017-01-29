package com.smallmin.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.smallmin.Enity.Category;

/** 
* @author jmlu
* @version 2017年1月29日 下午9:34:26 
*  
*/
@Repository
public interface CategotyDao extends CrudRepository<Category, Integer> {
	
}
