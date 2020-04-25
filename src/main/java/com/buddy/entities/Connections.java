package com.buddy.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Connections implements Serializable{

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date datecreation;
	private Long iduser_con;
	
	@ManyToOne
	@JoinColumn(name = "iduser")
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDatecreation() {
		return datecreation;
	}

	public void setDatecreation(Date datecreation) {
		this.datecreation = datecreation;
	}

	public Long getIduser_con() {
		return iduser_con;
	}

	public void setIduser_con(Long iduser_con) {
		this.iduser_con = iduser_con;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Connections() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Connections(Date datecreation, Long iduser_con, User user) {
		super();
		this.datecreation = datecreation;
		this.iduser_con = iduser_con;
		this.user = user;
	}
	
	
	
}
