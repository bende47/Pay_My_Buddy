package com.buddy.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("RE")
public class Retrait extends Transactions{

	public Retrait() {
		super();
	}

	public Retrait(String description, Double amount, Double amount_red, Double amount_vers, Date datecreation,
			Long idcpte_rec, Compte compte) {
		super(description, amount, amount_red, amount_vers, datecreation, idcpte_rec, compte);
		// TODO Auto-generated constructor stub
	}

	
	


	
}
