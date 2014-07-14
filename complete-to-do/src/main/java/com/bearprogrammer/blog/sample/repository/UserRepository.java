package com.bearprogrammer.blog.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bearprogrammer.blog.sample.User;

public interface UserRepository extends PagingAndSortingRepository<User, Integer>, UserDetailsService {

	@Query("select u.username from User u")
	public List<String> findUsernames();
	
	public User findByUsername(String username);
	
	@Override
	@Query("select u from User u where username = :username")
	public UserDetails loadUserByUsername(@Param("username") String username) throws UsernameNotFoundException;
	
}