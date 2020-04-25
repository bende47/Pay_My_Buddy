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
	
	@GetMapping(value="/verser/{idcpte}/{description}/{montant}")
	public Boolean verser(@PathVariable Long idcpte,@PathVariable String description,@PathVariable  Double montant) {
		return transactionMetier.verser(idcpte, description, montant);
	}
	
	@GetMapping(value="/retirer/{idcpte}/{description}/{montant}")
	public Boolean retirer(@PathVariable Long idcpte,@PathVariable String description,@PathVariable  Double montant) {
		return transactionMetier.retirer(idcpte, description, montant);
	}
	
	@GetMapping(value="/virement/{iduser}/{iduserrec}/{description}/{amount}")
	public Boolean virement(@PathVariable Long iduser,@PathVariable Long iduserrec,@PathVariable String description,@PathVariable Double amount) {		
		return transactionMetier.virement(iduser, iduserrec, description, amount);
	}
	
	@GetMapping(value="/getAllTransaction")
	public Page<Transactions> getAllTransaction(@RequestParam(name="page", defaultValue = "0") int page, @RequestParam(name="size", defaultValue = "3") int size) {		
		return transactionMetier.listTransactions(page, size);
	}
	
	@GetMapping(value="getCompteUserById/{iduser}")
	public Compte getCompteUserById(@PathVariable Long iduser) {
		return transactionMetier.getCompteUserById(iduser);
	}
	
	@GetMapping(value="versementRetrait/{iduser}/{amount}/{typeoperation}")
	public Boolean versementRetrait(@PathVariable Long iduser,@PathVariable Double amount, @PathVariable String typeoperation) {
		
		if("Versement".equals(typeoperation)) {
			return transactionMetier.verser(iduser, "", amount);
		}else {
			return transactionMetier.retirer(iduser, "", amount);
		}
	}
	
	@GetMapping(value="addCompte/{solde}/{iduser}")
	public Compte addCompte(@PathVariable Double solde,@PathVariable Long iduser) {
		return transactionMetier.addCompte(solde, iduser);
	}
	
	
}
