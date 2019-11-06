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
			rs.getLong("list_id"),
			rs.getLong("user_id"),
			rs.getString("name")
			);
	
	
	@Autowired
	public JdbcTaskListDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
				.withTableName("task_list")
				.usingGeneratedKeyColumns("list_id");
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
	public Optional<TaskList> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(TaskList entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateDescription(TaskList entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateIsDone(TaskList entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<TaskList> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateName(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TaskList> findAllByUserId(Long id) {
		return jdbcTemplate.query("SELECT * FROM task_list WHERE user_id =?", taskListMapper, id);
	}


	


}
