package com.example.todorestful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.todorestful.dao.JdbcUserDAO;
import com.example.todorestful.dao.UserDAO;
import com.example.todorestful.model.User;
import com.example.todorestful.util.UserUtility;

@RestController
@RequestMapping("/users")
public class UserController {
	private JdbcUserDAO userDAO;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private UserUtility userUtility;
	
	public UserController(UserDAO userDAO, BCryptPasswordEncoder bCryptPasswordEncoder, UserUtility userUtility) {
		this.userDAO = (JdbcUserDAO) userDAO;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userUtility = userUtility;
	}

	@PostMapping("/sign-up")
	public void signUp(@RequestBody User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userDAO.save(user);
	}
	
	@GetMapping("/username")
	@ResponseBody
	public String getUser() {
		User username = userUtility.getUsernameFromSecurityContextHolder();
		return "{ \"username\" : \"" + username.getUsername() + "\"}";
	}
	
}
