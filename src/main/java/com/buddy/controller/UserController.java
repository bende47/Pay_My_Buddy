package com.buddy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.buddy.entities.User;
import com.buddy.metier.UserMetier;

@RestController
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserMetier userMetier;
	
	
	/**
	 * Login user
	 */
	@GetMapping(value="/login/{email}/{password}")
	public User login(@PathVariable String email,@PathVariable String password) {
		return userMetier.login(email, password);
	}
	
	/**
	 * Ajout d'un user
	 */
	
	@PostMapping(value="/addUser")
	public User addUser(@RequestBody User user) {
		return userMetier.addUser(user);
	}
	
	/**
	 * Liste des users compte actifs
	 */
	
	@GetMapping(value="/allUser")
	public List<User> allUser() {
		return userMetier.listUserCompteActif();
	}
	
}
