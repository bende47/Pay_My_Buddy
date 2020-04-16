package com.buddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.buddy.entities.User;
import com.buddy.metier.UserMetier;

@RestController
public class UserController {

	@Autowired
	private UserMetier userMetier;
	
	@PostMapping(value="/addUser")
	public User addUser(@RequestBody User user) {
		return userMetier.addUser(user);
	}
	
	@GetMapping(value="/deleteUser/{iduser}")
	public User deleteUser(@PathVariable Long iduser) {
		return userMetier.deleteUser(iduser);
	}
	
}
