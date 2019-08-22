package com.example.todorestful;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.todorestful.dao.JdbcTodoDAO;
import com.example.todorestful.model.Todo;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class TestTodoDAO {
	@Autowired
	JdbcTodoDAO todoDAO;
	
	@Test 
	public void testSave() {
		Todo todo = new Todo();
		todo.setDescription("Get er done");
		
		Todo savedTodo = todoDAO.save(todo);
		assertNotNull(savedTodo.getId());
	}
	
	@Test 
	public void testTodoIsDoneDefaultValue() {
		Todo todo = new Todo();
		todo.setDescription("Test1");
		
		Todo savedTodo = todoDAO.save(todo);
		
		Optional<Todo> optionalTodo = todoDAO.findById(savedTodo.getId());
		Todo fetchedTodo = optionalTodo.get();
		
		assertNotNull(fetchedTodo.getId());
		assertEquals(false, fetchedTodo.isDone());
	}
	
	@Test 
	public void testTodoIsDoneTrue() {
		Todo todo = new Todo();
		todo.setDescription("Get er done");
		todo.setDone(true);
		
		Todo savedTodo = todoDAO.save(todo);
		
		Optional<Todo> optionalTodo = todoDAO.findById(savedTodo.getId());
		Todo fetchedTodo = optionalTodo.get();
		assertNotNull(fetchedTodo.getId());
		assertEquals(true, fetchedTodo.isDone());
	}
	
	@Test
	public void testTodoDelete() {
		Todo todo = new Todo();
		todo.setDescription("Get er done");
		todo.setDone(true);
		
		Todo savedTodo = todoDAO.save(todo);
		todoDAO.delete(savedTodo);
		assertEquals(false, todoDAO.existsById(savedTodo.getId()));
	}
	
	@Test
	public void testUpdateDescription() {
		Todo todo = new Todo();
		todo.setDescription("Get er done");
		todo.setDone(true);
		
		Todo savedTodo = todoDAO.save(todo);
		todo.setDescription("update");
		todoDAO.updateDescription(todo);
		
		Optional<Todo> optionalTodo = todoDAO.findById(savedTodo.getId());
		Todo fetchedTodo = optionalTodo.get();
		assertEquals("update", fetchedTodo.getDescription());
		
	}
	
	
	@Test
	public void testUpdateIsDone() {
		Todo todo = new Todo();
		todo.setDescription("Get er done");
		
		Todo savedTodo = todoDAO.save(todo);
		todo.setDescription("update");
		
		todo.setDone(true);
		todoDAO.updateIsDone(todo);
		
		Optional<Todo> optionalTodo = todoDAO.findById(savedTodo.getId());
		Todo fetchedTodo = optionalTodo.get();
		assertEquals(true, fetchedTodo.getDescription());

	
	}
	
	@Test
	public void testCount() {
		assertEquals(11, todoDAO.count());

	}
}
