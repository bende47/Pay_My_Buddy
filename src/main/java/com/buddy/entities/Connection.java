package com.buddy.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Connection implements Serializable{

	@Id @GeneratedValue
	private Long id;
	private Date datecreation;
	private int iduser_con;
	
	@ManyToOne
	@JoinColumn(name = "iduser")
	private User user;
	
}
