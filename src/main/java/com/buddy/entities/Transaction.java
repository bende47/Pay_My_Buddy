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
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction implements Serializable{
	
	@Id @GeneratedValue
	private Long idtrans;
	private String description;
	private Double amount;
	private Double amount_red;
	private Double amount_vers;
	private Date datecreation;
	private int idcpte_rec;
	
	@ManyToOne
	@JoinColumn(name = "idcpte")
	private Compte compte;
}
