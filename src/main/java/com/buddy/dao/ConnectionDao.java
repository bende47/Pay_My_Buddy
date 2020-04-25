package com.buddy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.buddy.entities.Connections;
import com.buddy.entities.User;

@Repository
public interface ConnectionDao extends JpaRepository<Connections, Long>{
	
	@Query("select u from User u INNER JOIN Connections c ON c.iduser_con = u.iduser where c.user.iduser =:iduser ")
	public List<User> getConnectionByUserId(@Param("iduser") Long iduser);
	
	@Query("select c from Connections c where c.id =:id ")
	public Connections getConnectionById(@Param("id") Long id);
}
