package com.buddy.metier;

import org.springframework.data.domain.Page;

import com.buddy.entities.Compte;
import com.buddy.entities.Transactions;

public interface TransactionMetier {

	/**
	 * 
	 * @param idcpte
	 * @param description
	 * @param montant
	 * @return true or false
	 */
	public Boolean verser(Long idcpte, String description, Double montant);
	
	/**
	 * 
	 * @param idcpte
	 * @param description
	 * @param montant
	 * @return true or false
	 */
	public Boolean retirer(Long idcpte, String description, Double montant);
	
	/**
	 * 
	 * @param idcpte
	 * @param idcpterec
	 * @param description
	 * @param montant
	 * @return true or false
	 */
	public Boolean virement(Long idcpte, Long idcpterec, String description, Double montant);
	
	/**
	 * 
	 * @param page
	 * @param size
	 * @return la liste des transactions
	 */
	public Page<Transactions> listTransactions(Long compte,int page,int size);
	
	/**
	 * 
	 * @param idUser
	 * @return compte du user a partir de son Id
	 */
	public Compte getCompteUserById(Long idUser);
	

}
