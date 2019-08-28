package com.example.todorestful.model;

import javax.persistence.Entity;

public class Todo {
	private Integer id;
	private Integer listId;
	private String description;
	private boolean isDone;
	
	public Todo() {
		
	}
	public Todo(String description, boolean isDone) {
		this.description = description;
		this.isDone = isDone;
	}
	
	public Todo(Integer id, Integer listId, String description) {
		this.id = id;
		this.listId = listId;
		this.description = description;
	}
	
	public Todo(Integer id, Integer listId, String description, boolean isDone) {
		this.id = id;
		this.listId = listId;
		this.description = description;
		this.isDone = isDone;
	}
	


	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getListId() {
		return listId;
	}

	public void setListId(Integer listId) {
		this.listId = listId;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean isDone() {
		return isDone;
	}
	
	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
	
	
	
	
}
