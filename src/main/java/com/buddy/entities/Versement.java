package com.buddy.entities;


import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("VS")
public class Versement extends Transactions {

	public Versement() {
		super();
	}

	public Versement(String description, Double amount, Double amount_red, Double amount_vers, Date datecreation,
			Long idcpte_rec, Compte compte) {
		super(description, amount, amount_red, amount_vers, datecreation, idcpte_rec, compte);
		// TODO Auto-generated constructor stub
	}

	
	
}
