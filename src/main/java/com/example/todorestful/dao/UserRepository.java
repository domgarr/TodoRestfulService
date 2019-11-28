package com.example.todorestful.dao;

import com.example.todorestful.model.User;

public interface UserRepository {
	User findByUsername(String username);
	User save(User user);
}
