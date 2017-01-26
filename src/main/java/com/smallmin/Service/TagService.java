package com.smallmin.Service;

import java.util.Collection;

import com.smallmin.Enity.Tag;

/** 
* @author jmlu
* @version 2017年1月26日 下午7:03:57 
*  
*/
public interface TagService {
	Tag add(Tag tag);
	Tag update(Tag tag);
	void delete(Integer id);
	Tag getOne(Integer id);
	Collection<Tag> getAll();
	
	
}
