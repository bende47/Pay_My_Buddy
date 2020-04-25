package com.buddy;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import com.buddy.dao.UserDao;
import com.buddy.entities.User;
import com.buddy.metier.UserMetierImpl;


@SpringBootTest
@TestPropertySource(locations="classpath:application.properties")
public class UserMetierTest {
	
	public static UserMetierImpl userMetierImpl;
	
	@Autowired
	private UserDao userDao;
	
	
	@Test	
	@Sql({"/buddyTest.sql"})
	@DisplayName("Add User")	
	public void addUserTest() {
		
		String password = BCrypt.hashpw("azerty", BCrypt.gensalt());
		User users =new User("messou@gmail.com", password, "Messou", "Arsene", "0789654123",true);
		users = userDao.save(users);
		
		User u = userDao.getUserByEmail("messou@gmail.com");
		assertEquals("Messou", u.getNom());
		assertEquals("Arsene", u.getPrenoms());
	}
	
	
	@Test
	@Sql({"/buddyTest.sql"})
	@DisplayName("Login User")
	public void loginTest() {	
		
		String password = BCrypt.hashpw("azerty", BCrypt.gensalt());
		User users =new User("messou@gmail.com", password, "Messou", "Arsene", "0789654123",true);
		userDao.save(users);
		
		User user = userDao.getUserByEmail("messou@gmail.com");		
		if (user != null && BCrypt.checkpw("azerty", user.getPassword())) {
			user.setDatederniereconnexion(new Date());
			userDao.save(user);
		}
		
		assertEquals("Messou", user.getNom());
	}
	
	@Test
	@Sql({"/buddyTest.sql"})
	@DisplayName("Liste User compte actif")
	public void listUserCompteActifTest() {
		
		String password = BCrypt.hashpw("azerty", BCrypt.gensalt());
		User users =new User("messou@gmail.com", password, "Messou", "Arsene", "0789654123",true);
		users = userDao.save(users);		
		
		User user = new User("koffi@gmail.com", password, "Messou", "Emeline", "0589653214", true);
		user = userDao.save(user);		
		
		List<User> listuserActif = userDao.getAllUserCompteActif();
		
		assertEquals(listuserActif.get(0).getNom(), listuserActif.get(1).getNom());
		
	}
	

}
