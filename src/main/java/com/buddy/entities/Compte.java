package com.buddy.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Compte implements Serializable {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idcpte;
	private Double solde;
	private Date datecreation;
 
	@JsonIgnore
	@OneToMany(mappedBy = "compte", cascade = CascadeType.ALL)
	private Collection<Transactions> transactions;	
	
	@ManyToOne
	@JoinColumn(name = "iduser")
	private User user;

	public Long getIdcpte() {
		return idcpte;
	}

	public void setIdcpte(Long idcpte) {
		this.idcpte = idcpte;
	}

	public Double getSolde() {
		return solde;
	}

	public void setSolde(Double solde) {
		this.solde = solde;
	}

	public Date getDatecreation() {
		return datecreation;
	}

	public void setDatecreation(Date datecreation) {
		this.datecreation = datecreation;
	}

	public Collection<Transactions> getTransactions() {
		return transactions;
	}

	public void setTransactions(Collection<Transactions> transactions) {
		this.transactions = transactions;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Compte() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Compte(Double solde, Date datecreation, User user) {
		super();
		this.solde = solde;
		this.datecreation = datecreation;
		this.user = user;
	}
	
	
	
	
	
}
