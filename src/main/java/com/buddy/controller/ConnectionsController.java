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
	
	@Autowired(required=true)
	private ConnectionMetier connectionMetier;

	@GetMapping(value="/addConnection/{iduser}/{email}")
	public Connections addConnection(@PathVariable Long iduser,@PathVariable String email) {
		return connectionMetier.addConnection(iduser, email);
	}
	
//	@GetMapping(value="/deleteConnection/{idc}")
//	public Boolean deleteConnection(@PathVariable Long idc ){
//		return connectionMetier.deleteConnection(idc);
//	}
	
	@GetMapping(value="/listConnection/{idc}")
	public List<User> listConnection(@PathVariable Long idc ){
		return connectionMetier.getConnectionByUser(idc);
	}
	
}
