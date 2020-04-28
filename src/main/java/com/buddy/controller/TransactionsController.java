package com.buddy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.buddy.entities.Compte;
import com.buddy.entities.Transactions;
import com.buddy.metier.TransactionMetier;

@RestController
@CrossOrigin("*")
public class TransactionsController {
 
	@Autowired(required=true)
	private TransactionMetier transactionMetier;
	
	/**
	 * Versement sur le compte 
	 */
	
	@GetMapping(value="/verser/{idcpte}/{description}/{montant}")
	public Boolean verser(@PathVariable Long idcpte,@PathVariable String description,@PathVariable  Double montant) {
		return transactionMetier.verser(idcpte, description, montant);
	}
	
	/**
	 * Retrait sur le compte
	 */
	
	@GetMapping(value="/retirer/{idcpte}/{description}/{montant}")
	public Boolean retirer(@PathVariable Long idcpte,@PathVariable String description,@PathVariable  Double montant) {
		return transactionMetier.retirer(idcpte, description, montant);
	}
	
	/**
	 * Virement d'un compte a un autre
	 */
	
	@GetMapping(value="/virement/{iduser}/{iduserrec}/{description}/{amount}")
	public Boolean virement(@PathVariable Long iduser,@PathVariable Long iduserrec,@PathVariable String description,@PathVariable Double amount) {		
		return transactionMetier.virement(iduser, iduserrec, description, amount);
	}
	
	/**
	 * Liste des transaction effectuée sur un compte
	 */
	
	@GetMapping(value="/getAllTransaction")
	public Page<Transactions> getAllTransaction(
			@RequestParam(name="idcpte") Long idcpte,
			@RequestParam(name="page", defaultValue = "0") int page, 
			@RequestParam(name="size", defaultValue = "3") int size) {		
		return transactionMetier.listTransactions(idcpte,page, size);
	}
	
	/**
	 * Recuperation du compte du user
	 */
	
	@GetMapping(value="getCompteUserById/{iduser}")
	public Compte getCompteUserById(@PathVariable Long iduser) {
		return transactionMetier.getCompteUserById(iduser);
	}
	
	/**
	 * Faire un retrait ou un versement
	 */
	
	@GetMapping(value="versementRetrait/{iduser}/{amount}/{typeoperation}")
	public Boolean versementRetrait(@PathVariable Long iduser,@PathVariable Double amount, @PathVariable String typeoperation) {
		
		if("Versement".equals(typeoperation)) {
			return transactionMetier.verser(iduser, "", amount);
		}else {
			return transactionMetier.retirer(iduser, "", amount);
		}
	}
	
	
	
	
}
