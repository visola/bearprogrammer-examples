package com.bearprogrammer.blog.buildtool.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bearprogrammer.blog.buildtool.dao.PersonRepository;
import com.bearprogrammer.blog.buildtool.model.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/META-INF/spring/test.xml")
public class TestPersonRepository {
	
	@Autowired
	PersonRepository personRepository;
	
	@Test
	public void saveShouldWork() {
		Person p = new Person("John Doe");
		p = personRepository.save(p);
		assertNotNull("Should have generated an id.", p.getId());
	}
	
}