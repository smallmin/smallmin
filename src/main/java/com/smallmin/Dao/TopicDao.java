package com.smallmin.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.smallmin.Enity.Topic;

/** 
* @author jmlu
* @version 2017年1月28日 上午1:07:28 
*  
*/
@Repository
public interface TopicDao extends CrudRepository<Topic, Integer> {

}
