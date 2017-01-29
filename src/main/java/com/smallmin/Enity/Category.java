package com.smallmin.Enity;
/** 
* @author jmlu
* @version 2017年1月29日 下午9:30:41 
*  
*/

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Category{
	@Id
	@GeneratedValue
	private int id;
	private String content;
	
	public Category() {
		super();
	}
	public Category(int id, String content) {
		super();
		this.id = id;
		this.content = content;
	}
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
	
}