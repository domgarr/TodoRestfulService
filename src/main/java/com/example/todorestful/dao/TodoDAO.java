package com.example.todorestful.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.todorestful.model.Todo;

@Repository
public class TodoDAO implements TodoRepository {
	/*
	 * https://docs.spring.io/spring/docs/2.0.x/reference/jdbc.html
	 * 
	 * JDBC Template:
	 * Creates and Release the resources.
	 * Thread safe.
	 */
	
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert jdbcInsert;
	
	@Autowired
	public TodoDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
				.withTableName("todos")
				.usingGeneratedKeyColumns("id");
				
	}
	
	@Override
	public Todo save(Todo todo) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("description", todo.getDescription());
	
		Integer generatedId = (Integer) jdbcInsert.executeAndReturnKey(parameters);
		todo.setId(generatedId);
		
		return todo;
	}

	@Override
	public Iterable saveAll(Iterable entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable findAllById(Iterable ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void deleteAll(Iterable entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Todo> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Todo entity) {
		// TODO Auto-generated method stub
		
	}
	
}
