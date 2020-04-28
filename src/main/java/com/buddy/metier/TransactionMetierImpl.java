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
		if(compte!=null) {
			transaction = new Versement("Versement de "+ amount + " € sur mon compte", amount, 0.0, amount, new Date(), null,compte);
			transactionDao.save(transaction);
			if(transactionDao.save(transaction)!=null) {
				compte.setSolde(compte.getSolde() + amount);
				logger.info("Versement de "+ amount + " € sur mon compte");
				return true;
			}else {
				return false;

			}
		}else {
			return false;
		}
		
	}
	
	@Transactional
	@Override
	public Boolean retirer(Long iduser, String description, Double amount) {
		compte = compteDao.getCompteUserByIduser(iduser);
		if(compte!=null) {
			transaction = new Retrait("Retrait de "+ amount + " € sur mon compte", amount, 0.0, amount, new Date(), null,compte);
			
			if(transactionDao.save(transaction)!=null) {
				compte.setSolde(compte.getSolde() - amount);
				logger.info("Retrait de "+ amount + " € sur mon compte");
				return true;
			}else {
				return false;

			}
		}else {
			return false;
		}
	}

	@Transactional
	@Override
	public Boolean virement(Long iduser, Long iduserrec, String description, Double amount) {	
		retirer(iduser, description, amount);		
		Compte compteUser = compteDao.getCompteUserByIduser(iduser);
		compte = compteDao.getCompteUserByIduser(iduserrec);
		
		if(compte!=null && compteUser!=null) {			

			Double montantverse = amount - (amount*0.005);
			transaction = new Versement("Virement de " + montantverse + " € à " + compte.getUser().getNom() +" " + compte.getUser().getPrenoms(), amount, amount*0.005, montantverse, new Date(), compteUser.getIdcpte(),compte);
			
			if(transactionDao.save(transaction)!=null) {
				compte.setSolde(compte.getSolde() + (amount - (amount*0.005)));	
				logger.info("Retrait de "+ amount + " € sur mon compte et versement de " + montantverse + " € à " + compte.getUser().getNom() +" " + compte.getUser().getPrenoms() );
				return true;	
			}else {
				return false;
			}
		}else {
			return false;
		}
		
	}

	@Override
	public Page<Transactions> listTransactions(Long idcompte,int page, int size) {
	
		Page<Transactions> pages = transactionDao.getAllTransaction(PageRequest.of(page, size));

		return pages;
	}

	@Override
	public Compte getCompteUserById(Long idUser) {
		return compteDao.getCompteUserByIduser(idUser);
	}

	

}
