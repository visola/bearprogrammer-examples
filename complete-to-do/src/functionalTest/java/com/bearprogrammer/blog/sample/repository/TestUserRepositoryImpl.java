package com.bearprogrammer.blog.sample.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bearprogrammer.blog.sample.User;
import com.bearprogrammer.blog.sample.test.BaseFunctionalTest;

public class TestUserRepositoryImpl extends BaseFunctionalTest {
	
	User someone;
	
	@PersistenceContext
	EntityManager entityManager;
	
	UserRepositoryImpl subjectUnderTest;
	
	@Before
	public void setup() {
		someone = new User();
		someone.setUsername("someone");
		someone.setPassword("password");
		
		entityManager.persist(someone);
		
		Assert.assertNotNull(someone.getId());
		
		subjectUnderTest = new UserRepositoryImpl();
		subjectUnderTest.entityManager = entityManager;
	}
	
	@Test
	public void testLoadUserByUsernameFindsUser() throws Exception {
		UserDetails user = subjectUnderTest.loadUserByUsername(someone.getUsername());
		Assert.assertNotNull(user);
	}
	
	@Test(expected=UsernameNotFoundException.class)
	public void testLoadUserByUsernameDoesNotFindUser() throws Exception {
		subjectUnderTest.loadUserByUsername("NotAUser");
	}

}
