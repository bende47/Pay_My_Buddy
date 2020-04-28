package com.buddy.metier;

import java.util.List;

import com.buddy.entities.User;

public interface UserMetier {
	/**
	 * 
	 * @param email
	 * @param password
	 * @return user connecté
	 */
	public User login(String email, String password);
	
	/**
	 * 
	 * @param user
	 * @return le user crée
	 */
	public User addUser(User user);
	
	/**
	 * 
	 * @return la liste des User ayant le compte actif
	 */
	public List<User> listUserCompteActif();
}
