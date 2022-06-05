package com.vet.web.app.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="adopt")
@Getter
@Setter
public class Adopter extends User{

    private Boolean hasFamily;

    @OneToMany(mappedBy="adopt")
    private List<Pet> pets;

}
