package com.example.todorestful;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.todorestful.dao.JdbcTaskListDAO;
import com.example.todorestful.dao.TaskListDAO;
import com.example.todorestful.model.TaskList;



@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class TestTaskListDAO extends TodoRestfulApplicationTests {
	
	@Autowired
	TaskListDAO todoListDAO;
	
	@Test
	public void testTodoListSave() {
		TaskList todoList = new TaskList();
		todoList.setName("Morning Routine");
		todoList.setUserId((long)1);
		
		TaskList savedTodoList = todoListDAO.save(todoList);
		
		assertNotNull(savedTodoList.getListId());
		
	}
	
	@Test
	public void testTodoDelete() {
		TaskList todoList = new TaskList();
		todoList.setName("Morning Routine");
		todoList.setUserId((long)1);
		
		TaskList savedTodoList = todoListDAO.save(todoList);
		assertNotNull(savedTodoList.getListId());
		todoListDAO.delete(savedTodoList.getListId());
		
		boolean exists = todoListDAO.existsById(savedTodoList.getListId());
		
		assertEquals(false, exists);
	}
	
	@Test
	public void updateName() {
		TaskList todoList = new TaskList();
		todoList.setName("Morning Routine");
		todoList.setUserId((long)1);
		
		TaskList savedTodoList = todoListDAO.save(todoList);
		savedTodoList.setName("Name");
		todoListDAO.updateName(savedTodoList);
		
		Optional<TaskList> fetchedTaskList = todoListDAO.findById(savedTodoList.getListId());
		assertEquals("Name", fetchedTaskList.get().getName());
	}
}
