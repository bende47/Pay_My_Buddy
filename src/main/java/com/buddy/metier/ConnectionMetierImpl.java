package com.buddy.metier;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buddy.dao.ConnectionDao;
import com.buddy.dao.UserDao;
import com.buddy.entities.Connections;
import com.buddy.entities.User;

@Service
@Transactional
public class ConnectionMetierImpl implements ConnectionMetier {

	private static final Logger logger = LogManager.getRootLogger();

	@Autowired
	private ConnectionDao connectionDao;

	@Autowired
	private UserDao userDao;

	@Override
	public Connections addConnection(Long iduser, String email) {
		User user = userDao.getUserById(iduser);
		User us = userDao.getUserByEmail(email);
		if (us != null) {
			Connections connection = new Connections();
			connection.setDatecreation(new Date());
			connection.setIduser_con(us.getIduser());
			connection.setUser(user);
			connectionDao.save(connection);
			logger.info("Connection ajoutée avec succès ");
			return connection;

		} else {
			logger.info("User introuvable ");
			return null;
		}

	}

	@Override
	public List<User> getConnectionByUser(Long iduser) {
		return connectionDao.getConnectionByUserId(iduser);
	}

}
