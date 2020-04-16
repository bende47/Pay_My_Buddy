package com.buddy.metier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buddy.dao.UserDao;
import com.buddy.entities.User;

@Service
public class UserMetierImpl implements UserMetier{

	private static final Logger logger = LogManager.getRootLogger();
	private User user; 
	String hashed="";
	
	@Autowired
	private UserDao userDao;

	
	@Override
	public User addUser(User user) {
		try {
			
			 String email = BCrypt.hashpw(user.getEmail(), BCrypt.gensalt());
			 String password = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
			 
			 User us = userDao.getUserByEmail(email);
			 
			 if(us!=null) {
				 logger.info("User existe déjà ");
				 return us; 
			 }else {
				 user.setEmail(email);
				 user.setPassword(password);
				 
				 if(userDao.save(user)!=null) {
					 logger.info("User ajouté avec succès ");
					 return user;
				 }else {
					 logger.info("Une erreur est survenue");
					 return null;
				 }
			 }
			
			
		} catch (Exception e) {
			logger.error("Erreur lors de l'ajout d'un user " + e.toString());
			return null;
		}
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUserById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
