package com.example.todorestful;

import static org.junit.Assert.assertNotNull;

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
public class TestTodoListDAO extends TodoRestfulApplicationTests {
	
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
}
