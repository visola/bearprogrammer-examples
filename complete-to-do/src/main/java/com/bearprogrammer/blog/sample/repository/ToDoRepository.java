package com.bearprogrammer.blog.sample.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bearprogrammer.blog.sample.ToDo;

public interface ToDoRepository extends PagingAndSortingRepository<ToDo,Integer> {
	
	List<ToDo> findByAssignedToUsernameOrCreatedByUsername(String assignedTo, String createdBy);

}
