package com.buddy.metier;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buddy.dao.UserDao;
import com.buddy.entities.User;

@Service
public class UserMetierImpl implements UserMetier {

	private static final Logger logger = LogManager.getRootLogger();
	private User user;
	String hashed = "";

	@Autowired(required=true)
	private UserDao userDao;

	@Override
	public User login(String email, String password) {
		try {
			
			user = userDao.getUserByEmail(email);
			
			if (user != null && BCrypt.checkpw(password, user.getPassword())) {
				logger.info("User connecté avec succes");
				user.setDatederniereconnexion(new Date());
				userDao.save(user);
				return user;

			} else {
				logger.info("User introuvable");
				return null;
			}

		} catch (Exception e) {
			logger.error("Erreur de connexion " + e);
			return null;
		}
	}

	@Override
	public User addUser(User user) {
		try {

			String password = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

			User us = userDao.getUserByEmail(user.getEmail());

			if (us != null) {
				logger.info("User existe déjà ");
				return us;
			} else {
				User u = new User(user.getEmail(), password, user.getNom(), user.getPrenoms(), user.getContact(), true);
				userDao.save(u); 
				logger.info("User ajouté avec succès ");
				return u;
			}

		} catch (Exception e) {
			logger.error("Erreur lors de l'ajout d'un user " + e);
			return null;
		}
	}

//	@Override
//	public User updateUser(User user) {
//		try {
//			User us = userDao.getUserById(user.getIduser());
//			if (us != null) {
//				user.setEmail(us.getEmail());
//				user.setPassword(us.getPassword());
//				user.setStatut(us.getStatut());
//				if (userDao.save(user) != null) {
//					logger.info("User modifié avec succès ");
//					return user;
//				} else {
//					logger.info("Une erreur est survenue");
//					return null;
//				}
//			} else {
//				return null;
//			}
//
//		} catch (Exception e) {
//			logger.error("Erreur lors de la modification d'un user " + e);
//			return null;
//		}
//	}
//
//	@Override
//	public User deleteUser(Long iduser) {
//		try {
//			user = new User();
//			user = userDao.getUserById(iduser);
//			if (user != null) {
//				user.setStatut(false);
//				userDao.save(user);
//				logger.info("User supprimé avec succès ");
//			}
//			return user;
//
//		} catch (Exception e) {
//			logger.error("Erreur lors du changement de statut d'un user " + e);
//			return null;
//		}
//	}

	@Override
	public List<User> listUserCompteActif() {
		try {
			return userDao.getAllUserCompteActif();
		} catch (Exception e) {
			logger.error("Erreur de recuperation de la liste des User " + e);
			return null;
		}
	}

}
