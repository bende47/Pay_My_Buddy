package com.buddy.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{

	@Id @GeneratedValue
	private Long iduser;
	@Column(unique = true)
	private String email;
	private String password;
	private String nom;
	private String prenoms;
	private String contact;
	private Date datederniereconnexion;
	private boolean statut;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Collection<Connection> connections;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Collection<Compte> comptes;
}
