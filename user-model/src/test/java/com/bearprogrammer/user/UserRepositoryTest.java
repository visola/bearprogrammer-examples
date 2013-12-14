package com.bearprogrammer.user;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bearprogrammer.user.User;
import com.bearprogrammer.user.UserRepository;
import com.bearprogrammer.user.WrongPasswordException;

@ContextConfiguration(locations={"classpath:/META-INF/spring/test-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback=true)
@Transactional(propagation=Propagation.NESTED)
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private String userName, password, passwordEncrypted, newPassword, newPasswordEncrypted;
	
	@Before
	public void setup() {
		userName = "username";
		password = "password";
		passwordEncrypted = passwordEncoder.encodePassword(password, userName);
		
		newPassword = "newPassword";
		newPasswordEncrypted = passwordEncoder.encodePassword(newPassword, userName);
	}
	
	@Test
	public void saveShouldWork() {
		userRepository.save(new User(userName, password));
		User user = userRepository.findOne(userName);
		Assert.assertNotNull("User must have been saved.", user);
	}
	
	@Test
	public void passwordMustBeEncoded() {
		User user = userRepository.save(new User(userName, password));
		Assert.assertEquals("Password must be encoded using username as key.", passwordEncrypted, user.getPassword());
	}
	
	@Test
	public void emptyPasswordShouldBeSavedAsNull () {
		User user = userRepository.save(new User(userName, ""));
		Assert.assertNull("Empty password should be saved as null.", user.getPassword());
	}
	
	@Test
	public void loadUserByUserNameShouldWork() {
		userRepository.save(new User(userName, password));
		
		UserDetails userLoaded = userRepository.loadUserByUsername(userName);
		Assert.assertTrue("Should have loaded user.", userLoaded.getClass().isAssignableFrom(User.class));
		
		User user = (User) userLoaded;
		Assert.assertEquals("User must have been loaded correctly.", new User(userName, passwordEncrypted), user);
	}
	
	@Test(expected=UsernameNotFoundException.class)
	public void loadUserByUserNameShouldThrowExceptionForNonExistingUserName() {
		userRepository.loadUserByUsername("not a user");
	}
	
	@Test
	public void saveShouldNotChangePassword() {
		userRepository.save(new User(userName, password));
		userRepository.save(new User(userName, newPassword));
		User user = userRepository.findOne(userName);
		Assert.assertEquals("Save must not change password.", passwordEncrypted, user.getPassword());
	}
	
	@Test
	public void changePasswordShouldEncodePasswordUsingUsernameAsKey() {
		userRepository.save(new User(userName, password));
		userRepository.changePassword(userName, password, newPassword);
		User user = userRepository.findOne(userName);
		
		Assert.assertNotSame("Password must have been changed.", passwordEncrypted, user.getPassword());
		Assert.assertEquals("New password must be encoded using username as key.", newPasswordEncrypted, user.getPassword());
	}
	
	@Test
	public void changePasswordShouldWorkWithOldEncryptedPassword() {
		userRepository.save(new User(userName, password));
		userRepository.changePassword(userName, passwordEncrypted, newPassword);
		User user = userRepository.findOne(userName);
		
		Assert.assertNotSame("Password must have been changed.", passwordEncrypted, user.getPassword());
		Assert.assertEquals("New password must be encoded using username as key.", newPasswordEncrypted, user.getPassword());
	}
	
	@Test(expected=UsernameNotFoundException.class)
	public void changePasswordShouldThrowExceptionForNonExistingUser () {
		userRepository.changePassword("not a user", "whatever", "and ever");
	}
	
	@Test
	public void changePasswordToEmptyShouldSaveItAsNull() {
		userRepository.save(new User(userName, password));
		userRepository.changePassword(userName, password, "");
		User user = userRepository.findOne(userName);
		Assert.assertNull("Change password to empty should save it as null.", user.getPassword());
	}
	
	@Test(expected=WrongPasswordException.class)
	public void changePasswordShouldThrowExceptionWithWrongPassword() {
		userRepository.save(new User(userName, password));
		userRepository.changePassword(userName, "wrong password", newPassword);
	}

}
