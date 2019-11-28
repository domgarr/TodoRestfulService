package com.example.todorestful;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.todorestful.dao.JdbcUserDAO;
import com.example.todorestful.model.User;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestUserDAO extends TodoRestfulApplicationTests {
	
	@Autowired
	JdbcUserDAO userDAO;
	
	private User user1;
	
	@Before
	public void generateUser() {
	    user1 = new User();
		user1.setUsername("dome");
		user1.setPassword("pass");
	}

	@Test
	public void testA_userSave(){
		User savedUser = userDAO.save(user1);
		assertNotNull(savedUser.getId());
		
	}
	
	@Test
	public void testB_findByUsername() {
		//TODO: Fix class by not using @Transactional.
		//@Transaction rolls back after every @Test I belive. So best not to use that here.
		
		User savedUser = userDAO.save(user1);
		assertNotNull(savedUser.getId());
		
		User fetchedUser = userDAO.findByUsername(user1.getUsername());
		
		assertNotNull(fetchedUser.getId());
		assertEquals("dome", fetchedUser.getUsername());
	}

}
