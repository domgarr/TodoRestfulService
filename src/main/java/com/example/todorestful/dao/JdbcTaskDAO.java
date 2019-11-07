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

import com.example.todorestful.model.Task;

@Repository
public class JdbcTaskDAO implements TaskDAO {
	/*
	 * https://docs.spring.io/spring/docs/2.0.x/reference/jdbc.html
	 * 
	 * JDBC Template:
	 * Creates and Release the resources.
	 * Thread safe.
	 */
	
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert jdbcInsert;
	
	private RowMapper<Task> todoMapper = (rs, rowNum) -> new Task(
			rs.getLong("todo_id"),
			rs.getLong("list_id"),
			rs.getString("description"),
			rs.getBoolean("is_done")
			);
	
	@Autowired
	public JdbcTaskDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
				.withTableName("task")
				.usingGeneratedKeyColumns("user_id");
				
	}
	
	@Override
	public Task save(Task task) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("description", task.getDescription());
		parameters.put("is_done", task.isDone());
		parameters.put("list_id", task.getListId());
	
		Number generatedId =  jdbcInsert.executeAndReturnKey(parameters);
		task.setId(generatedId.longValue());
		
		return task;
	}

	@Override
	public void delete(Long id) {
		jdbcTemplate.update("DELETE FROM task WHERE todo_id=?", id);
	}

	@Override
	public Optional<Task> findById(Long id) {
		/*
		 * Query for object is expecting 1 row, no more or less. In the case where just one row isn't retrieved 
		 * and exception is thrown. 
		 */
		return Optional.ofNullable(jdbcTemplate.queryForObject(
				"SELECT * FROM todos WHERE todo_id=?", 
				(rs,rowNum) -> new Task( rs.getLong("todo_id"),
					rs.getLong("list_id"),
					rs.getString("description"),
					rs.getBoolean("is_done")),
				id
				));
	}

	@Override
	public List<Task> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM todos", Long.class);
	}

	@Override
	public boolean existsById(Long id) {
		return jdbcTemplate.queryForObject("SELECT EXISTS(SELECT 1 FROM todos WHERE todo_id=?)", Boolean.class, id);
	}

	@Override
	public void updateDescription(Task task) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateIsDone(Task task) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Task> findAllByListId(Long id) {
		return jdbcTemplate.query("SELECT * FROM task WHERE list_id=?", todoMapper ,id);
	}
	
}
