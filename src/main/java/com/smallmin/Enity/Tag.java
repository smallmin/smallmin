package com.smallmin.Enity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/** 
* @author jmlu
* @version 2017年1月26日 下午6:51:56 
*  
*/
@Entity
public class Tag {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String content;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Tag(int id, String content) {
		super();
		this.id = id;
		this.content = content;
	}

	public Tag() {
		super();
	}

}
