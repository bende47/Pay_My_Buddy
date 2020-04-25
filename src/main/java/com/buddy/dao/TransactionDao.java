package com.buddy.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.buddy.entities.Transactions;

@Repository
public interface TransactionDao extends JpaRepository<Transactions, Long>{

	@Query("select t from Transactions t ")
	public Page<Transactions> getAllTransaction(Pageable pageable);
	
}
