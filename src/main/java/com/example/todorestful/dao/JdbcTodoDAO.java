package com.example.todorestful.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.todorestful.model.Todo;

@Repository
public class JdbcTodoDAO implements TodoDAO {
	/*
	 * https://docs.spring.io/spring/docs/2.0.x/reference/jdbc.html
	 * 
	 * JDBC Template:
	 * Creates and Release the resources.
	 * Thread safe.
	 */
	
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert jdbcInsert;
	
	private RowMapper<Todo> todoMapper = (rs, rowNum) -> new Todo(
			rs.getInt("todo_id"),
			rs.getInt("list_id"),
			rs.getString("description"),
			rs.getBoolean("is_done")
			);
	
	@Autowired
	public JdbcTodoDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
				.withTableName("todos")
				.usingGeneratedKeyColumns("id");
				
	}
	
	@Override
	public Todo save(Todo todo) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("description", todo.getDescription());
		parameters.put("is_done", todo.isDone());
		parameters.put("list_id", todo.getListId());
	
		Number generatedId =  jdbcInsert.executeAndReturnKey(parameters);
		todo.setId(generatedId.intValue());
		
		return todo;
	}

	@Override
	public void delete(Todo todo) {
		jdbcTemplate.update("DELETE FROM todos WHERE todo_id=?", todo.getId());
	}

	@Override
	public Optional<Todo> findById(Integer id) {
		/*
		 * Query for object is expecting 1 row, no more or less. In the case where just one row isn't retrieved 
		 * and exception is thrown. 
		 */
		return Optional.ofNullable(jdbcTemplate.queryForObject(
				"SELECT * FROM todos WHERE todo_id=?", 
				(rs,rowNum) -> new Todo( rs.getInt("todo_id"),
					rs.getInt("list_id"),
					rs.getString("description"),
					rs.getBoolean("is_done")),
				id
				));
	}

	@Override
	public List<Todo> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM todos", Long.class);
	}

	@Override
	public boolean existsById(Integer id) {
		return jdbcTemplate.queryForObject("SELECT EXISTS(SELECT 1 FROM todos WHERE todo_id=?)", Boolean.class, id);
	}

	@Override
	public void updateDescription(Todo todo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateIsDone(Todo todo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Todo> findAllByListId(Integer id) {
		return jdbcTemplate.query("SELECT * FROM todos WHERE list_id=?", todoMapper ,id);
	}
	
}
