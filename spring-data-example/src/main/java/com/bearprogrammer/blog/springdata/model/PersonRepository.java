package com.bearprogrammer.blog.springdata.model;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface PersonRepository extends PagingAndSortingRepository<Person, Integer> {
	
	public List<Person> findByName(String name);

}
