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

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class User implements Serializable{

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long iduser;
	@Column(unique = true)
	private String email;
	private String password;
	private String nom;
	private String prenoms;
	private String contact;
	private Date datederniereconnexion;
	private Boolean statut;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Collection<Connections> connections;
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Collection<Compte> comptes;
	public Long getIduser() {
		return iduser;
	}
	public void setIduser(Long iduser) {
		this.iduser = iduser;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenoms() {
		return prenoms;
	}
	public void setPrenoms(String prenoms) {
		this.prenoms = prenoms;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public Date getDatederniereconnexion() {
		return datederniereconnexion;
	}
	public void setDatederniereconnexion(Date datederniereconnexion) {
		this.datederniereconnexion = datederniereconnexion;
	}
	public Boolean getStatut() {
		return statut;
	}
	public void setStatut(Boolean statut) {
		this.statut = statut;
	}
	public Collection<Connections> getConnections() {
		return connections;
	}
	public void setConnections(Collection<Connections> connections) {
		this.connections = connections;
	}
	public Collection<Compte> getComptes() {
		return comptes;
	}
	public void setComptes(Collection<Compte> comptes) {
		this.comptes = comptes;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String email, String password, String nom, String prenoms, String contact, Boolean statut) {
		super();
		this.email = email;
		this.password = password;
		this.nom = nom;
		this.prenoms = prenoms;
		this.contact = contact;
		this.statut = statut;
	}
	
	
	
	
}
