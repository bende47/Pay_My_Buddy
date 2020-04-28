package com.buddy;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import com.buddy.dao.ConnectionDao;
import com.buddy.dao.UserDao;
import com.buddy.entities.Connections;
import com.buddy.entities.User;

@SpringBootTest
@TestPropertySource(locations="classpath:application.properties")
public class ConnectionMetierTest {

	@Autowired
	private UserDao userDao;
	@Autowired
	private ConnectionDao connectionDao;
	
	@Test
	@Sql({"/buddyTest.sql"}) 
	public void addConnection() {
		
		String password = BCrypt.hashpw("azerty", BCrypt.gensalt());
		User user1 =new User("messou@gmail.com", password, "Messou", "Arsene", "0789654123",true);
		user1 = userDao.save(user1);		
		
		User user2 = new User("koffi@gmail.com", password, "Messou", "Emeline", "0589653214", true);
		user2 = userDao.save(user2);		
	
		Connections connection = new Connections(new Date(), user2.getIduser(), user1);
		
		assertEquals(user2.getIduser(), connection.getIduser_con());
		
		
	}
	
	
	@Test
	@Sql({"/buddyTest.sql"}) 
	public void getConnectionByUser() {	
	
		String password = BCrypt.hashpw("azerty", BCrypt.gensalt());
		User user1 =new User("messou@gmail.com", password, "Messou", "Arsene", "0789654123",true);
		user1 = userDao.save(user1);		
		
		User user2 = new User("koffi@gmail.com", password, "Messou", "Emeline", "0589653214", true);
		user2 = userDao.save(user2);
		
		User user3 = new User("regigouale@gmail.com", password, "Regi", "Gouale", "0496523125", true);
		user3 = userDao.save(user3);
		
		
		Connections connection = new Connections(new Date(), user2.getIduser(), user1);
		connectionDao.save(connection);
		Connections connection1 = new Connections(new Date(), user3.getIduser(), user1);
		connectionDao.save(connection1);

		List<User> listConnectionUser = connectionDao.getConnectionByUserId(user1.getIduser());
		
		assertEquals(user1.getNom(), listConnectionUser.get(0).getNom());

	}
	
	

}
