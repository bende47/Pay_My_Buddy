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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Compte implements Serializable {

	@Id @GeneratedValue
	private Long idcpte;
	private Double solde;
	private Date datecreation;
 
	@OneToMany(mappedBy = "compte", cascade = CascadeType.ALL)
	private Collection<Transaction> transactions;	
	
	@ManyToOne
	@JoinColumn(name = "iduser")
	private User user;
	
	
}
