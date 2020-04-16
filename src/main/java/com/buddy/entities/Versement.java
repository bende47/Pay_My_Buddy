package com.buddy.entities;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("VS")
@NoArgsConstructor
public class Versement extends Transaction {
	
}
