package com.example.todorestful.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.todorestful.dao.JdbcUserDAO;
import com.example.todorestful.model.User;

@Component
public class UserUtility {
	@Autowired
	private JdbcUserDAO userDAO;
	
	public User getUsernameFromSecurityContextHolder() {
		String username = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(username == null) {
			//TODO: Throw a error? 
			return null;
		}
		
		User user = userDAO.findByUsername(username);
		return user;
	}
}
