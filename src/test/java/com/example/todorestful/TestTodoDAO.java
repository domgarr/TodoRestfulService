package com.example.todorestful;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.todorestful.dao.JdbcTaskDAO;
import com.example.todorestful.dao.JdbcTaskListDAO;
import com.example.todorestful.model.Task;
import com.example.todorestful.model.TaskList;


public class TestTodoDAO extends TodoRestfulApplicationTests {
	Long listId;
	
	@Autowired
	JdbcTaskDAO todoDAO;
	@Autowired
	JdbcTaskListDAO todoListDAO;
	
	@Before
	public void saveTodoList() {
		TaskList todoList = new TaskList();
		todoList.setName("Morning routine");
		todoList.setUserId((long)1);
		TaskList savedTodoList = todoListDAO.save(todoList);
		listId = savedTodoList.getListId();
	}
	
	@Test 
	public void testSave() {
		Task todo = new Task();
		todo.setDescription("Get er done");
		todo.setListId(listId);
		
		Task savedTodo = todoDAO.save(todo);
		assertNotNull(savedTodo.getId());
	}
	
	@Test 
	public void testTodoIsDoneDefaultValue() {
		Task todo = new Task();
		todo.setDescription("Test1");
		todo.setListId(listId);

		
		Task savedTodo = todoDAO.save(todo);
		
		Optional<Task> optionalTodo = todoDAO.findById(savedTodo.getId());
		Task fetchedTodo = optionalTodo.get();
		
		assertNotNull(fetchedTodo.getId());
		assertEquals(false, fetchedTodo.isDone());
	}
	
	@Test 
	public void testTodoIsDoneTrue() {
		Task todo = new Task();
		todo.setDescription("Get er done");
		todo.setDone(true);
		todo.setListId(listId);

		
		Task savedTodo = todoDAO.save(todo);
		
		Optional<Task> optionalTodo = todoDAO.findById(savedTodo.getId());
		Task fetchedTodo = optionalTodo.get();
		assertNotNull(fetchedTodo.getId());
		assertEquals(true, fetchedTodo.isDone());
	}
	
	@Test
	public void testTodoDelete() {
		Task todo = new Task();
		todo.setDescription("Get er done");
		todo.setDone(true);
		todo.setListId(listId);

		
		Task savedTodo = todoDAO.save(todo);
		todoDAO.delete(savedTodo.getId());
		assertEquals(false, todoDAO.existsById(savedTodo.getId()));
	}
	
	@Test
	public void testUpdateDescription() {
		Task todo = new Task();
		todo.setDescription("Get er done");
		todo.setDone(true);
		todo.setListId(listId);

		
		Task savedTodo = todoDAO.save(todo);
		savedTodo.setDescription("update");
		todoDAO.updateDescription(savedTodo);
		
		Optional<Task> optionalTodo = todoDAO.findById(savedTodo.getId());
		Task fetchedTodo = optionalTodo.get();
		assertEquals("update", fetchedTodo.getDescription());
		
	}
	
	
	@Test
	public void testUpdateIsDone() {
		Task todo = new Task();
		todo.setDescription("Get er done");
		todo.setListId(listId);

		
		Task savedTodo = todoDAO.save(todo);
		todo.setDescription("update");
		
		todo.setDone(true);
		todoDAO.updateIsDone(todo);
		
		Optional<Task> optionalTodo = todoDAO.findById(savedTodo.getId());
		Task fetchedTodo = optionalTodo.get();
		assertEquals(true, fetchedTodo.isDone());
	}
	
	@Test
	public void testCount() {
		Task todo = new Task();
		todo.setDescription("Get er done");
		todo.setDone(true);
		todo.setListId(listId);

		todoDAO.save(todo);
		
		assertEquals(1, todoDAO.count()); //In the future use a test db for testing.
	}
	
	@Test
	public void testFindAllByListId() {
		Task todo1 = new Task();
		todo1.setDescription("Drink cup of water");
		todo1.setListId(listId);
		
		todoDAO.save(todo1);
		
		Task todo2 = new Task();
		todo2.setDescription("Make coffee");
		todo2.setListId(listId);
		
		todoDAO.save(todo2);
		
		ArrayList<Task> todos = (ArrayList)todoDAO.findAllByListId(listId);
		
		assertEquals("Drink cup of water", todos.get(0).getDescription());
		assertEquals("Make coffee", todos.get(1).getDescription());
	}
	
	@Test
	public void testUpdateTask() {
		Task todo1 = new Task();
		todo1.setDescription("Drink cup of water");
		todo1.setListId(listId);
		todoDAO.save(todo1);
		
		todo1.setDescription("Name");
		todoDAO.updateDescription(todo1);
		
		Optional<Task> fetchedTask = todoDAO.findById(todo1.getId());
		assertEquals("Name", fetchedTask.get().getDescription());
		
	}
	
	@Test
	public void testTaskIsDoneUpdate() {
		Task todo1 = new Task();
		todo1.setDescription("Drink cup of water");
		todo1.setListId(listId);
		todo1 = todoDAO.save(todo1); //Set todo1 to be the returned Task/Todo that will have a generated id.
		
		Task todo2 = new Task();
		todo2.setDescription("Drink cup of water");
		todo2.setListId(listId);
		todo2 = todoDAO.save(todo2);
		
		todo1.setDone(true);
		todo2.setDone(true);
		
		List<Task> tasks = new ArrayList<>();
		tasks.add(todo1);
		tasks.add(todo2);
		
		todoDAO.batchUpdateIsDone(tasks);
		
		Optional<Task> fetchedTask1 = todoDAO.findById(todo1.getId());
		assertEquals(true, fetchedTask1.get().isDone());
		
		Optional<Task> fetchedTask2 = todoDAO.findById(todo2.getId());
		assertEquals(true, fetchedTask2.get().isDone());
		
		
	}
	
	
}
