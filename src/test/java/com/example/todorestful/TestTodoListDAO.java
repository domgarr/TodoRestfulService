package com.example.todorestful;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.todorestful.dao.JdbcTodoListDAO;
import com.example.todorestful.dao.TodoListDAO;
import com.example.todorestful.model.TodoList;



@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class TestTodoListDAO extends TodoRestfulApplicationTests {
	
	@Autowired
	TodoListDAO todoListDAO;
	
	@Test
	public void testTodoListSave() {
		TodoList todoList = new TodoList();
		todoList.setDescription("Morning Routine");
		todoList.setUserId(1);
		
		TodoList savedTodoList = todoListDAO.save(todoList);
		
		assertNotNull(savedTodoList.getId());
		
	}
}
