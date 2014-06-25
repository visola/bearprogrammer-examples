package com.bearprogrammer.blog.sample.repository;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.bearprogrammer.blog.sample.ToDo;
import com.bearprogrammer.blog.sample.ToDoHistory;

public class TestToDoRepositoryImpl extends BaseFunctionalTest {
	
	@PersistenceContext
	EntityManager entityManager;
	
	ToDoRepositoryImpl subjectUnderTest;
	
	@Before
	public void setup() {
		subjectUnderTest = new ToDoRepositoryImpl();
		subjectUnderTest.entityManager = entityManager;
	}
	
	@Test
	public void testSaveNewToDo() {
		ToDo toDo = createToDo();
		ToDo saved = subjectUnderTest.save(toDo);
		
		Assert.assertEquals("Should have generated ID.", new Integer(1), saved.getId());
		
		ToDoHistory history = entityManager.find(ToDoHistory.class, 1);
		Assert.assertNotNull("Should have generated a history record.", history);
	}
	
	@Test
	public void testUpdateExistingToDo() {
		ToDo toDo = createToDo();
		ToDo saved = subjectUnderTest.save(toDo);
		
		String newAction = "New action";
		saved.setAction(newAction);
		
		ToDo newSaved = subjectUnderTest.save(saved);
		Assert.assertEquals(saved.getId(), newSaved.getId());
		
		ToDo loaded = entityManager.find(ToDo.class, saved.getId());
		Assert.assertEquals(newAction, loaded.getAction());
		
		long historyCount = (Long) entityManager.createQuery("select count(1) from ToDoHistory").getSingleResult();
		Assert.assertEquals(2, historyCount);
	}

	private ToDo createToDo() {
		ToDo toDo = new ToDo();
		toDo.setAction("Buy milk");
		toDo.setAssignedTo("someone");
		toDo.setCreated(Calendar.getInstance());
		toDo.setUpdated(Calendar.getInstance());
		toDo.setCreatedBy("someone");
		toDo.setUpdatedBy("someone");
		return toDo;
	}

}