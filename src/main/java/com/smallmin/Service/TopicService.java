package com.smallmin.Service;

import java.util.Collection;


import com.smallmin.Enity.Topic;

/** 
* @author jmlu
* @version 2017年1月28日 上午1:05:28 
*  
*/
public interface TopicService {
	Topic add(Topic topic);
	Topic update(Topic topic);
	void delete(Integer id);
	Topic getOne(Integer id);
	Collection<Topic> getAll();
}
