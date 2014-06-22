package com.bearprogrammer.blog.sample.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.bearprogrammer.blog.sample.ToDo;
import com.bearprogrammer.blog.sample.ToDoHistory;

public class ToDoRepositoryImpl {
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Transactional
	public ToDo save(ToDo toDo) {
		if (toDo.getId() == null) {
			entityManager.persist(toDo);
		} else {
			// Load old record from database
			ToDo loaded = entityManager.find(ToDo.class, toDo.getId());
			
			// Make sure created and created by do not change
			toDo.setCreated(loaded.getCreated());
			toDo.setCreatedBy(loaded.getCreatedBy());
			
			toDo = entityManager.merge(toDo);
		}
		
		// History will use data from To Do item
		ToDoHistory history = new ToDoHistory(toDo);
		
		// Save history record
		entityManager.persist(history);
		
		return toDo;
	}

}
