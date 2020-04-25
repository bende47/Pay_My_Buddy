package com.buddy.metier;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buddy.dao.CompteDao;
import com.buddy.dao.TransactionDao;
import com.buddy.dao.UserDao;
import com.buddy.entities.Compte;
import com.buddy.entities.Retrait;
import com.buddy.entities.Transactions;
import com.buddy.entities.User;
import com.buddy.entities.Versement;


@Service
@Transactional
public class TransactionMetierImpl implements TransactionMetier{
	
	private static final Logger logger = LogManager.getRootLogger();


	@Autowired
	private CompteDao compteDao;
	
	@Autowired
	private TransactionDao transactionDao;
	
	private Compte compte ;
	private Transactions transaction ;
	
	@Autowired	
	private UserDao userDao;
	
	@Transactional
	@Override
	public Boolean verser(Long iduser, String description, Double amount) {
		compte = compteDao.getCompteUserByIduser(iduser);
		transaction = new Versement("Versement de "+ amount + " € sur mon compte", amount, 0.0, amount, new Date(), null,compte);
		transactionDao.save(transaction);
		if(transactionDao.save(transaction)!=null) {
			compte.setSolde(compte.getSolde() + amount);
			logger.info("Versement de "+ amount + " € sur mon compte");
			return true;
		}else {
			return false;

		}
	}
	
	@Transactional
	@Override
	public Boolean retirer(Long iduser, String description, Double amount) {
		compte = compteDao.getCompteUserByIduser(iduser);
		transaction = new Retrait("Retrait de "+ amount + " € sur mon compte", amount, 0.0, amount, new Date(), null,compte);
		
		if(transactionDao.save(transaction)!=null) {
			compte.setSolde(compte.getSolde() - amount);
			logger.info("Retrait de "+ amount + " € sur mon compte");
			return true;
		}else {
			return false;

		}
	}

	@Transactional
	@Override
	public Boolean virement(Long iduser, Long iduserrec, String description, Double amount) {	
		retirer(iduser, description, amount);		
		
		Double montantverse = amount - (amount*0.005);
		compte = compteDao.getCompteUserByIduser(iduserrec);
		transaction = new Versement("Virement de " + montantverse + " € à " + compte.getUser().getNom() +" " + compte.getUser().getPrenoms(), amount, amount*0.005, montantverse, new Date(), iduserrec,compte);
		
		if(transactionDao.save(transaction)!=null) {
			compte.setSolde(compte.getSolde() + (amount - (amount*0.005)));	
			logger.info("Retrait de "+ amount + " € sur mon compte et versement de " + montantverse + " € à " + compte.getUser().getNom() +" " + compte.getUser().getPrenoms() );
			return true;	
		}else {
			return false;
		}
		
		
		
	}

	@Override
	public Page<Transactions> listTransactions(int page, int size) {
	
		Page<Transactions> pages = transactionDao.getAllTransaction(PageRequest.of(page, size));

		return pages;
	}

	@Override
	public Compte getCompteUserById(Long idUser) {
		return compteDao.getCompteUserByIduser(idUser);
	}

	@Override
	public Compte addCompte(Double solde,Long iduser) {
		try {
			User user = userDao.getUserById(iduser);
			Compte compte = null;
			
			if(user!=null) {
				compte = new Compte(solde, new Date(), user);
				logger.info("Compte creé avec succès");
				compteDao.save(compte);
			}
			return compte;
			
		} catch (Exception e) {
			logger.info("Erreur lors de l'ajout du compte "+e);
			return null;
		}
	}

}
