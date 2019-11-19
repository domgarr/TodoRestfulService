package com.example.todorestful.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.todorestful.model.Task;
import com.example.todorestful.model.TaskList;

@Repository
public class JdbcTaskListDAO implements TaskListDAO {
	
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert jdbcInsert;
	
	private RowMapper<TaskList> taskListMapper = (rs, rowNum) -> new TaskList(
			rs.getLong("id"),
			rs.getLong("user_id"),
			rs.getString("name")
			);
	
	
	@Autowired
	public JdbcTaskListDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
				.withTableName("task_lists")
				.usingGeneratedKeyColumns("id");
	}

	@Override
	public TaskList save(TaskList taskList) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("user_id", taskList.getUserId());
		parameters.put("name", taskList.getName());
		
		Long newId = jdbcInsert.executeAndReturnKey(parameters).longValue();
		taskList.setListId(newId);
		return taskList;
	}

	@Override
	public Optional<TaskList> findById(Long id) {
		return Optional.ofNullable(jdbcTemplate.queryForObject(
				"SELECT * FROM task_lists WHERE id=?", 
				(rs,rowNum) -> new TaskList( rs.getLong("id"),
					rs.getLong("user_id"),
					rs.getString("name")
				), id));
	}

	@Override
	public List<TaskList> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(Long id) {
		jdbcTemplate.update("DELETE FROM task_lists WHERE id=?", id);		
	}

	@Override
	public boolean existsById(Long id) {
			return jdbcTemplate.queryForObject("SELECT EXISTS(SELECT 1 FROM task_lists WHERE id=?)", Boolean.class, id);
	}

	@Override
	public void updateName(TaskList taskList) {
		jdbcTemplate.update("UPDATE task_lists SET name=? WHERE id = ?", taskList.getName(), taskList.getListId());
	}

	@Override
	public List<TaskList> findAllByUserId(Long id) {
		return jdbcTemplate.query("SELECT * FROM task_lists WHERE user_id =?", taskListMapper, id);
	}


	


}
