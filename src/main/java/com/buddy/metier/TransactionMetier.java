package com.buddy.metier;

import org.springframework.data.domain.Page;

import com.buddy.entities.Compte;
import com.buddy.entities.Transactions;

public interface TransactionMetier {

	public Boolean verser(Long idcpte, String description, Double montant);
	public Boolean retirer(Long idcpte, String description, Double montant);
	public Boolean virement(Long idcpte, Long idcpterec, String description, Double montant);
	public Page<Transactions> listTransactions(int page,int size);
	public Compte getCompteUserById(Long idUser);
	public Compte addCompte(Double solde,Long iduser);
}
