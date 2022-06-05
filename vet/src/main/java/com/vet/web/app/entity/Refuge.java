package com.vet.web.app.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/*
    The mapping-related configurations will be done
    useing JPA annotations in the model classes
 */

@Entity
@Table(name="refuge")
@Getter
@Setter
public class Refuge extends User{

    @OneToMany(mappedBy="refuge")
    private List<Pet> pets;

}
