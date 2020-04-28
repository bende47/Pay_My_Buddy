package com.buddy.metier;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buddy.dao.CompteDao;
import com.buddy.dao.UserDao;
import com.buddy.entities.Compte;
import com.buddy.entities.User;

@Service
@Transactional
public class UserMetierImpl implements UserMetier {

	private static final Logger logger = LogManager.getRootLogger();
	private User user;
	String hashed = "";

	@Autowired(required = true)
	private UserDao userDao;

	@Autowired
	private CompteDao compteDao;

	@Override
	public User login(String email, String password) {

		user = userDao.getUserByEmail(email);

		if (user != null && BCrypt.checkpw(password, user.getPassword())) {
			logger.info("User connecté avec succes");
			user.setDatederniereconnexion(new Date());
			user = userDao.save(user);

			return user;

		} else {
			logger.info("User introuvable");
			return null;
		}

	}

	@Override
	public User addUser(User user) {
		String password = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

		User us = userDao.getUserByEmail(user.getEmail());

		if (us != null) {
			logger.info("User existe déjà ");
			return us;
		} else {
			User u = new User(user.getEmail(), password, user.getNom(), user.getPrenoms(), user.getContact(), true);
			u = userDao.save(u);
			logger.info("User ajouté avec succès ");

			Compte compte = new Compte(100.0, new Date(), u);
			logger.info("Compte creé avec succès");
			compteDao.save(compte);

			return u;
		}

	}

	@Override
	public List<User> listUserCompteActif() {
		return userDao.getAllUserCompteActif();
	}

}
