package com.buddy.metier;

import java.util.List;

import com.buddy.entities.Connections;
import com.buddy.entities.User;

public interface ConnectionMetier {

	public Connections addConnection(Long iduser,String email);
//	public Boolean deleteConnection(Long idc);
	public List<User> getConnectionByUser(Long iduser);
}
