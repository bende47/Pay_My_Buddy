package com.buddy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.buddy.entities.Compte;

@Repository
public interface CompteDao extends JpaRepository<Compte, Long>{

	@Query("select c from Compte c where c.user.iduser =:iduser ")
	public Compte getCompteUserByIduser(@Param("iduser") Long iduser);
	
}
