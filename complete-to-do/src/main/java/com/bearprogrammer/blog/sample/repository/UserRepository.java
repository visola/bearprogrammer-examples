package com.bearprogrammer.blog.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.bearprogrammer.blog.sample.User;

public interface UserRepository extends PagingAndSortingRepository<User, Integer>, UserDetailsService {

	@Query("select u.username from User u")
	public List<String> findUsernames();
	
	public User findByUsername(String username);
	
}