package com.bearprogrammer.blog.sample.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bearprogrammer.blog.sample.User;

public class UserRepositoryImpl implements UserDetailsService {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Query query = entityManager.createQuery("select u from User u where u.username = :username");
		query.setParameter("username", username);
		
		try {
			return (User) query.getSingleResult();
		} catch (NoResultException nre) {
			throw new UsernameNotFoundException("User not found: " + username, nre);
		}
	}

}