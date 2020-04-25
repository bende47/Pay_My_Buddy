package com.buddy.metier;

import java.util.List;

import com.buddy.entities.User;

public interface UserMetier {
	public User login(String email, String password);
	public User addUser(User user);
//	public User updateUser(User user);
//	public User deleteUser(Long iduser);
	public List<User> listUserCompteActif();
}
