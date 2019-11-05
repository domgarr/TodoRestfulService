package com.example.todorestful.model;

import java.util.List;




public class TodoList {
	private Integer id;
	private Integer userId;
	private String description;
	private List<Task> todos;
	
	
	
	public TodoList() {
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Task> getTodos() {
		return todos;
	}
	public void setTodos(List<Task> todos) {
		this.todos = todos;
	}
	
	
}
