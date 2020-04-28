package com.buddy.metier;

import java.util.List;

import com.buddy.entities.Connections;
import com.buddy.entities.User;

public interface ConnectionMetier {

	/**
	 * 
	 * @param iduser
	 * @param email
	 * @return la connection crée
	 */
	public Connections addConnection(Long iduser,String email);
	
	
	/**
	 * 
	 * @param iduser
	 * @return la liste des connections du user
	 */
	
	public List<User> getConnectionByUser(Long iduser);
	
}
