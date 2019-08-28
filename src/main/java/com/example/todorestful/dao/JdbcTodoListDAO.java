package com.example.todorestful.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.todorestful.model.TodoList;

@Repository
public class JdbcTodoListDAO implements TodoListDAO {
	
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert jdbcInsert;
	
	
	@Autowired
	public JdbcTodoListDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
				.withTableName("todo_list")
				.usingGeneratedKeyColumns("list_id");
	}


	public TodoList save(TodoList entity) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("description", entity.getDescription());
		parameters.put("user_id", entity.getUserId());
		
		Integer newId = jdbcInsert.executeAndReturnKey(parameters).intValue();
		entity.setId(newId);
		return entity;
	}


	public Optional<TodoList> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	
	public List<TodoList> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}


	public void delete(TodoList entity) {
		// TODO Auto-generated method stub
		
	}


	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}


	public void updateDescription(TodoList entity) {
		// TODO Auto-generated method stub
		
	}


	public void updateIsDone(TodoList entity) {
		// TODO Auto-generated method stub
		
	}

	


}
