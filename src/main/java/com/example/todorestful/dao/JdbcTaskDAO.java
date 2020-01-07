package com.example.todorestful.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
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
			rs.getLong("id"),
			rs.getLong("list_id"),
			rs.getString("description"),
			rs.getBoolean("is_done")
			);
	
	@Autowired
	public JdbcTaskDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
				.withTableName("tasks")
				.usingGeneratedKeyColumns("id");
				
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
		jdbcTemplate.update("DELETE FROM tasks WHERE id=?", id);
	}

	@Override
	public Optional<Task> findById(Long id) {
		/*
		 * Query for object is expecting 1 row, no more or less. In the case where just one row isn't retrieved 
		 * and exception is thrown. 
		 */
		return Optional.ofNullable(jdbcTemplate.queryForObject(
				"SELECT * FROM tasks WHERE id=?", 
				(rs,rowNum) -> new Task( rs.getLong("id"),
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
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM tasks", Long.class);
	}

	@Override
	public boolean existsById(Long id) {
		return jdbcTemplate.queryForObject("SELECT EXISTS(SELECT 1 FROM tasks WHERE id=?)", Boolean.class, id);
	}

	@Override
	public void updateDescription(Task task) {
		jdbcTemplate.update("UPDATE tasks SET description=? WHERE id = ?", task.getDescription(), task.getId());
	}

	@Override
	public void batchUpdateIsDone(List<Task> tasks) {
		jdbcTemplate.batchUpdate("UPDATE tasks SET is_done=? WHERE id=?", new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setBoolean(1, tasks.get(i).isDone());
				ps.setLong(2, tasks.get(i).getId());
			}
			
			@Override
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return tasks.size();
			}
		});
		
	}

	@Override
	public List<Task> findAllByListId(Long id) {
		return jdbcTemplate.query("SELECT * FROM tasks WHERE list_id=?", todoMapper ,id);
	}

	@Override
	public void updateIsDone(Task task) {
		 jdbcTemplate.update("UPDATE tasks SET is_done =? WHERE id=?", task.isDone(), task.getId() );
	}
	
}
