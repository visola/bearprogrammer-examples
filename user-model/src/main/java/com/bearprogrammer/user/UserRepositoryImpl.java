package com.bearprogrammer.user;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class UserRepositoryImpl implements UserDetailsService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public void changePassword(String username, String currentPassword, String newPassword) {
		User loadedUser = entityManager.find(User.class, username);
		
		if (loadedUser == null) {
			throw new UsernameNotFoundException("Username not found: " + username); 
		}
		
		if ("".equals(newPassword)) {
			newPassword = null;
		}
		
		// Check current password
		if (!loadedUser.getPassword().equals(currentPassword)) {
			// Try encoding it
			String currentEncoded = passwordEncoder.encodePassword(currentPassword, username);
			if (!loadedUser.getPassword().equals(currentEncoded)) {
				throw new WrongPasswordException("Wrong password. Must have correct password to change existing password.");
			}
		}
		
		// Encode new password if not null
		String encodedPassword = newPassword == null ? newPassword : passwordEncoder.encodePassword(newPassword, username);
		loadedUser.setPassword(encodedPassword);
		
		entityManager.merge(loadedUser);
	}
	
	@Transactional
	public User save(User user) {
		User loadedUser = entityManager.find(User.class, user.getUsername());
		
		// Creating new user
		if (loadedUser == null) {
			String newPassword = user.getPassword();
			
			// If password is empty, set it to null
			if ("".equals(newPassword)) {
				newPassword = null;
			}
			
			user.setPassword( newPassword == null ? null : passwordEncoder.encodePassword(newPassword, user.getUsername()) );
			
			entityManager.persist(user);
		} else {
			// Avoid password changes through this method
			user.setPassword(loadedUser.getPassword());
			user = entityManager.merge(user);
		}
		
		return user;
	}

	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = entityManager.find(User.class, username);
		if (user == null) throw new UsernameNotFoundException("Username not found: " + username);
		return  user;
	}

}