package com.smallmin.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.smallmin.Enity.Tag;

/** 
* @author jmlu
* @version 2017年1月26日 下午7:00:14 
*  
*/
@Repository
public interface TagDao extends CrudRepository<Tag, Integer> {

}
