package com.vet.web.app.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="veterinarian")
@Getter
@Setter
public class Veterinarian extends User{

}
