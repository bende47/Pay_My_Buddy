package com.buddy.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("RE")
@NoArgsConstructor
public class Retrait extends Transaction{

}
