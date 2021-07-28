package com.smallmin.Service;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smallmin.Dao.TagDao;
import com.smallmin.Enity.Tag;

/** 
* @author jmlu
* @version 2017年1月26日 下午7:04:34 
*  
*/

@Service
public class TagServiceImpl implements TagService {

	@Autowired
	TagDao tagDao;
	
	public Tag add(Tag tag) {
		return tagDao.save(tag);
	}

	public Tag update(Tag tag) {
		return tagDao.save(tag);
	}

	public void delete(Integer id) {
		tagDao.delete(id);
	}

	public Tag getOne(Integer id) {
		return tagDao.findOne(id);
	}

	public Collection<Tag> getAll() {
		return (Collection<Tag>) tagDao.findAll();
	}

}
