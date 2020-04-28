package com.buddy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buddy.entities.Connections;
import com.buddy.entities.User;
import com.buddy.metier.ConnectionMetier;


@RestController
@CrossOrigin("*")
public class ConnectionsController {
	
	@Autowired
	private ConnectionMetier connectionMetier;

	/**
	 * Ajout d'une nouvelle connection
	 */
	
	@GetMapping(value="/addConnection/{iduser}/{email}")
	public Connections addConnection(@PathVariable Long iduser,@PathVariable String email) {
		return connectionMetier.addConnection(iduser, email);
	}
	
	/**
	 * Liste des connections du user
	 */
	
	@GetMapping(value="/listConnection/{idc}")
	public List<User> listConnection(@PathVariable Long idc ){
		return connectionMetier.getConnectionByUser(idc);
	}
	
}
