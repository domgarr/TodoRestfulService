package com.example.todorestful.model;

import javax.persistence.Entity;

public class Todo {
	private Integer id;
	private String description;
	boolean isDone;
	
	public Todo() {
		
	}
	
	public Todo(Integer id, String description, boolean isDone) {
		this.id = id;
		this.description = description;
		this.isDone = isDone;
	}
	
	public Todo(String description, boolean isDone) {
		this.description = description;
		this.isDone = isDone;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
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
