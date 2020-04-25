package com.buddy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.buddy.entities.User;

@Repository
public interface UserDao extends JpaRepository<User, Long>{

	@Query("select u from User u where u.email =:email ")
	public User getUserByEmail(@Param("email") String email);
	
	@Query("select u from User u where u.iduser =:id ")
	public User getUserById(@Param("id") Long id);
	
	@Query("select u from User u where u.statut=true")
	public List<User> getAllUserCompteActif();
}
