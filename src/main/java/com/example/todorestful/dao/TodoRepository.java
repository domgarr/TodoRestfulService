package com.example.todorestful.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.todorestful.model.Todo;

public interface TodoRepository extends CrudRepository<Todo, Long> {
	
}
