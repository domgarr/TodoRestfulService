package com.example.todorestful.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import com.example.todorestful.model.User;

@Repository
public class JdbcUserDAO implements UserDAO {
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcInsert jdbcInsert;
	
	
	private RowMapper<User> userMapper = (rs, rowNum) -> new User(
			rs.getLong("id"),
			rs.getString("username"),
			rs.getString("password")
			);
	
		
	@Autowired
	public JdbcUserDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
				.withTableName("users")
				.usingGeneratedKeyColumns("id");
	}

	@Override
	public User findByUsername(String username) {
		User user = (User) jdbcTemplate.queryForObject("Select * FROM users WHERE username=?", new Object[] {new String(username)}, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getLong("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				// TODO Auto-generated method stub
				return user;
			}
		});
		return user;
	}
	
	@Override
	public User save(User user) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("username", user.getUsername());
		parameters.put("password", user.getPassword());		
		Number id = jdbcInsert.executeAndReturnKey(parameters);
		user.setId(id.longValue());
		return user;
	}

	
}
