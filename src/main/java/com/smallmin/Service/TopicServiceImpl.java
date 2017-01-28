package com.smallmin.Service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smallmin.Dao.TagDao;
import com.smallmin.Dao.TopicDao;
import com.smallmin.Enity.Tag;
import com.smallmin.Enity.Topic;

/** 
* @author jmlu
* @version 2017年1月28日 上午1:06:33 
*  
*/
@Service
public class TopicServiceImpl implements TopicService {

	@Autowired
	TopicDao topicDao;
	
	public Topic add(Topic topic) {
		return topicDao.save(topic);
	}

	public Topic update(Topic topic) {
		return topicDao.save(topic);
	}

	public void delete(Integer id) {
		topicDao.delete(id);
	}

	public Topic getOne(Integer id) {
		return topicDao.findOne(id);
	}

	public Collection<Topic> getAll() {
		return (Collection<Topic>) topicDao.findAll();
	}

}
