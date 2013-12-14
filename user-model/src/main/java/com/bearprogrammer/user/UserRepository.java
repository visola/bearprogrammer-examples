package com.bearprogrammer.user;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserRepository extends PagingAndSortingRepository<User, String>, UserDetailsService {
	
	public void changePassword(String username, String currentPassword, String newPassword);
	
	/*
	 * Temporary solution to:
	 * http://forum.springsource.org/showthread.php?128530-Problem-with-custom-save(T)-implementation-Spring-Data-Commons-version-1-3-1-RELEASE
	 */
	@SuppressWarnings("unchecked")
	public User save(User user);
	
}
