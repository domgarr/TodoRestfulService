package com.example.todorestful.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/*
 * Jackson by default uses getters/setter and fields to serialize and deserialize to Json. That
 * is why if I used JsonProperty only on a field and not the getter/setter I was getting duplicate fields in the Json Response.
 * 
 * Look into making a global statement to override the default?
 * https://stackoverflow.com/questions/7105745/how-to-specify-jackson-to-only-use-fields-preferably-globally
 * 
 * For now, using Json Property twice does the job. 
 */

public class Task {
	
	@JsonProperty("taskId") //By using JSONPropery here, I can customize the field name. Without todoId would be Id
	private Integer taskId;
	
	private Integer listId;
	
	private String description;
	
	@JsonProperty("isDone")
	private boolean isDone;
	
	public Task() {
		
	}
	public Task(String description, boolean isDone) {
		this.description = description;
		this.isDone = isDone;
	}
	
	public Task(Integer userId, Integer listId, String description) {
		this.taskId = userId;
		this.listId = listId;
		this.description = description;
	}
	
	public Task(Integer userId, Integer listId, String description, boolean isDone) {
		this.taskId = userId;
		this.listId = listId;
		this.description = description;
		this.isDone = isDone;
	}
	
	//If JsonProperty isn't added to the getter, there will be duplicate 
	@JsonProperty("taskId")
	public Integer getId() {
		return taskId;
	}
	
	public void setId(Integer id) {
		this.taskId = id;
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
	
	@JsonProperty("isDone")
	public boolean isDone() {
		return isDone;
	}
	
	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
	
	
	
	
}
