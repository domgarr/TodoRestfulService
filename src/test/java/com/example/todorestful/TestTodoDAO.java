package com.example.todorestful;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.todorestful.dao.JdbcTodoDAO;
import com.example.todorestful.dao.JdbcTodoListDAO;
import com.example.todorestful.model.Todo;
import com.example.todorestful.model.TodoList;


public class TestTodoDAO extends TodoRestfulApplicationTests {
	Integer listId;
	
	@Autowired
	JdbcTodoDAO todoDAO;
	@Autowired
	JdbcTodoListDAO todoListDAO;
	
	@Before
	public void saveTodoList() {
		TodoList todoList = new TodoList();
		todoList.setDescription("Morning routine");
		todoList.setUserId(1);
		TodoList savedTodoList = todoListDAO.save(todoList);
		listId = savedTodoList.getId();
	}
	
	@Test 
	public void testSave() {
		Todo todo = new Todo();
		todo.setDescription("Get er done");
		todo.setListId(listId);
		
		Todo savedTodo = todoDAO.save(todo);
		assertNotNull(savedTodo.getId());
	}
	
	@Test 
	public void testTodoIsDoneDefaultValue() {
		Todo todo = new Todo();
		todo.setDescription("Test1");
		todo.setListId(listId);

		
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
		todo.setListId(listId);

		
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
		todo.setListId(listId);

		
		Todo savedTodo = todoDAO.save(todo);
		todoDAO.delete(savedTodo);
		assertEquals(false, todoDAO.existsById(savedTodo.getId()));
	}
	
	@Test
	public void testUpdateDescription() {
		Todo todo = new Todo();
		todo.setDescription("Get er done");
		todo.setDone(true);
		todo.setListId(listId);

		
		Todo savedTodo = todoDAO.save(todo);
		savedTodo.setDescription("update");
		todoDAO.updateDescription(savedTodo);
		
		Optional<Todo> optionalTodo = todoDAO.findById(savedTodo.getId());
		Todo fetchedTodo = optionalTodo.get();
		assertEquals("update", fetchedTodo.getDescription());
		
	}
	
	
	@Test
	public void testUpdateIsDone() {
		Todo todo = new Todo();
		todo.setDescription("Get er done");
		todo.setListId(listId);

		
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
		Todo todo = new Todo();
		todo.setDescription("Get er done");
		todo.setDone(true);
		todo.setListId(listId);

		todoDAO.save(todo);
		
		assertEquals(1, todoDAO.count());
	}
	
	@Test
	public void testFindAllByListId() {
		Todo todo1 = new Todo();
		todo1.setDescription("Drink cup of water");
		todo1.setListId(listId);
		
		todoDAO.save(todo1);
		
		Todo todo2 = new Todo();
		todo2.setDescription("Make coffee");
		todo2.setListId(listId);
		
		todoDAO.save(todo2);
		
		ArrayList<Todo> todos = (ArrayList)todoDAO.findAllByListId(listId);
		
		assertEquals("Drink cup of water", todos.get(0).getDescription());
		assertEquals("Make coffee", todos.get(1).getDescription());
	}
	
	
}
