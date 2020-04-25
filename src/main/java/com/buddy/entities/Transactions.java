package com.buddy.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_TRANS",discriminatorType = DiscriminatorType.STRING,length = 2)
public abstract class Transactions implements Serializable{
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idtrans;
	private String description;
	private Double amount;
	private Double amount_red;
	private Double amount_vers;
	private Date datecreation;
	private Long idcpte_rec;
	
	@ManyToOne
	@JoinColumn(name = "idcpte")
	private Compte compte;

	public Long getIdtrans() {
		return idtrans;
	}

	public void setIdtrans(Long idtrans) {
		this.idtrans = idtrans;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getAmount_red() {
		return amount_red;
	}

	public void setAmount_red(Double amount_red) {
		this.amount_red = amount_red;
	}

	public Double getAmount_vers() {
		return amount_vers;
	}

	public void setAmount_vers(Double amount_vers) {
		this.amount_vers = amount_vers;
	}

	public Date getDatecreation() {
		return datecreation;
	}

	public void setDatecreation(Date datecreation) {
		this.datecreation = datecreation;
	}

	public Long getIdcpte_rec() {
		return idcpte_rec;
	}

	public void setIdcpte_rec(Long idcpte_rec) {
		this.idcpte_rec = idcpte_rec;
	}

	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

	public Transactions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transactions(String description, Double amount, Double amount_red, Double amount_vers, Date datecreation,
			Long idcpte_rec, Compte compte) {
		super();
		this.description = description;
		this.amount = amount;
		this.amount_red = amount_red;
		this.amount_vers = amount_vers;
		this.datecreation = datecreation;
		this.idcpte_rec = idcpte_rec;
		this.compte = compte;
	}

	
	
	
	
	
	
	
}
