package com.example.todorestful.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.todorestful.dao.JdbcTaskListDAO;
import com.example.todorestful.dao.JdbcUserDAO;
import com.example.todorestful.dao.MyUserDetailsService;
import com.example.todorestful.model.TaskList;
import com.example.todorestful.model.User;
import com.example.todorestful.util.UserUtility;

@RestController
public class TodoListController {
	
	private JdbcTaskListDAO todoListDAO;
	private UserUtility userUtility;
	
	public TodoListController(JdbcTaskListDAO todoListDAO,  UserUtility userUtility) {
		this.todoListDAO = todoListDAO;
		this.userUtility = userUtility;
	}

	@PostMapping("/tasklist")
	TaskList newTaskList(@RequestBody TaskList newTaskList) {
		User user = userUtility.getUsernameFromSecurityContextHolder();
		
		if(user != null) {
		newTaskList.setUserId(user.getId());
		return todoListDAO.save(newTaskList);
		}
		return null;
	}
	
	@GetMapping("/tasklist")
	List<TaskList> getTaskListByUserId(){
		User user = userUtility.getUsernameFromSecurityContextHolder();

		if(user != null ) {
			
			List<TaskList> list = todoListDAO.findAllByUserId(user.getId());
			System.out.print(list.toString());
			return list;
		}
		
		return null;
		}
	
	
	@DeleteMapping("/tasklist/{id}")
	public void deleteTaskList(@PathVariable Long id){
		todoListDAO.delete(id);
	}
	
	@PutMapping("/tasklist")
	public void updateTaskList(@RequestBody TaskList taskList) {
		System.out.println(taskList.getListId());
		todoListDAO.updateName(taskList);
	}
	

}
