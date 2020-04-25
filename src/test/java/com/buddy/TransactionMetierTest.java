package com.buddy;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import com.buddy.dao.CompteDao;
import com.buddy.dao.TransactionDao;
import com.buddy.dao.UserDao;
import com.buddy.entities.Compte;
import com.buddy.entities.Transactions;
import com.buddy.entities.User;
import com.buddy.entities.Versement;
import com.buddy.entities.Retrait;

@SpringBootTest
@TestPropertySource(locations="classpath:application.properties")
class TransactionMetierTest {

	@Autowired
	private CompteDao compteDao;
	
	@Autowired
	private TransactionDao transactionDao;	

	@Autowired	
	private UserDao userDao;
	
	@Test
	@Sql({"/buddyTest.sql"})
	@Transactional
	public void addCompte() {
		
		String password = BCrypt.hashpw("azerty", BCrypt.gensalt());
		User users = new User("messou@gmail.com", password, "Messou", "Arsene", "0789654123",true);
		users = userDao.save(users);
		
		Compte compte = new Compte(275.00, new Date(), users);		
		compteDao.save(compte);
		
		Compte c = compteDao.getCompteUserByIduser(users.getIduser());
		
		assertEquals(c.getUser().getIduser(), users.getIduser());
	}
	
	@Test
	@Sql({"/buddyTest.sql"})
	@Transactional
	public void getCompteUserById() {
		
		String password = BCrypt.hashpw("azerty", BCrypt.gensalt());
		User users = new User("messou@gmail.com", password, "Messou", "Arsene", "0789654123",true);
		users = userDao.save(users);
		
		Compte compte = new Compte(275.00, new Date(), users);		
		compte = compteDao.save(compte);
		
		Compte cp = compteDao.getCompteUserByIduser(users.getIduser());
		
		assertEquals(compte.getSolde(), cp.getSolde());
		
	}
	
	@Test
	@Sql({"/buddyTest.sql"})
	@Transactional
	public void versement() {		
		
		String password = BCrypt.hashpw("azerty", BCrypt.gensalt());
		User users = new User("messou@gmail.com", password, "Messou", "Arsene", "0789654123",true);
		users = userDao.save(users);
		
		Compte compte = new Compte(200.00, new Date(), users);		
		compte = compteDao.save(compte);
		
		Transactions transactions = new Versement("Versement de "+ Integer.valueOf(10) + " € sur mon compte", 10.0, 0.0, 10.0, new Date(), null, compte);
		transactionDao.save(transactions);
		compte.setSolde(compte.getSolde() + 10.0);
		
		Compte comptes = compteDao.getCompteUserByIduser(users.getIduser());
		
		assertEquals( 210.00, comptes.getSolde());

	}
	
	@Test
	@Sql({"/buddyTest.sql"})
	@Transactional
	public void Retrait() {		
		
		String password = BCrypt.hashpw("azerty", BCrypt.gensalt());
		User users = new User("messou@gmail.com", password, "Messou", "Arsene", "0789654123",true);
		users = userDao.save(users);
		
		Compte compte = new Compte(200.00, new Date(), users);		
		compte = compteDao.save(compte);
		
		Transactions transactions = new Retrait("Retrait de "+ Integer.valueOf(10) + " € sur mon compte", 10.0, 0.0, 10.0, new Date(), null, compte);
		transactionDao.save(transactions);
		compte.setSolde(compte.getSolde() - 10.0);
		
		Compte comptes = compteDao.getCompteUserByIduser(users.getIduser());
		
		assertEquals( 190.00, comptes.getSolde());

	}
	
	
	@Test
	@Sql({"/buddyTest.sql"})
	@Transactional
	public void Virement() {	
		
		String password = BCrypt.hashpw("azerty", BCrypt.gensalt());
		
		User users = new User("messou@gmail.com", password, "Messou", "Arsene", "0789654123",true);
		users = userDao.save(users);		
		Compte compte = new Compte(200.00, new Date(), users);		
		compte = compteDao.save(compte);
		
		User user2 = new User("koffi@gmail.com", password, "Messou", "Emeline", "0589653214", true);
		user2 = userDao.save(user2);		
		Compte comptes = new Compte(100.00, new Date(), user2);		
		comptes = compteDao.save(comptes);		
		
		Transactions transactions = new Retrait("Retrait de "+ Integer.valueOf(10) + " € sur mon compte", 10.0, 0.0, 10.0, new Date(), null, compte);
		transactionDao.save(transactions);
		compte.setSolde(compte.getSolde() - 10.0);
		
		Double montantverse = 10 - (10*0.005);
		
		Transactions transactionss = new Versement("Virement de " + montantverse + " € à " + comptes.getUser().getNom() +" " + comptes.getUser().getPrenoms(), 10.0, 10.0*0.005, montantverse, new Date(), comptes.getIdcpte(), compte);	
		transactionDao.save(transactionss);
		comptes.setSolde(comptes.getSolde() + 9.95);
		
		assertEquals(190.0, compte.getSolde());
		assertEquals(109.95, comptes.getSolde());

	}
		
		
	

}
