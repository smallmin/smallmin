package com.smallmin.Enity;





import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/** 
* @author jmlu
* @version 2017年1月28日 上午12:59:33 
*  
*/
@Entity
public class Topic {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String title;
	private int authorId;
	private int categoryId;
	private int priority;
	private Date editTime;
	
	public Topic(int id, String title, int authorId, int categoryId, int priority, Date editTime) {
		super();
		this.id = id;
		this.title = title;
		this.authorId = authorId;
		this.categoryId = categoryId;
		this.priority = priority;
		this.editTime = editTime;
	}
	public Topic() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public Date getEditTime() {
		return editTime;
	}
	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
}
