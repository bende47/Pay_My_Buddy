package com.buddy.metier;

import com.buddy.entities.User;

public interface UserMetier {

	public User addUser(User user);
	public User updateUser(User user);
	public User deleteUser(Long iduser);
}
