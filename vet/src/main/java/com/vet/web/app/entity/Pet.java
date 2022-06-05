package com.vet.web.app.entity;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="pet")
public class Pet {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,
            length = 50)
    private String name;

    @Column(length = 50)
    private String age;

    @Column()
    private Double weight;

    @Column()
    private Size size;

    @Column()
    private Boolean isSterilized;

    @Column(length = 200)
    private String description;

    @ManyToOne
    @JoinColumn(name = "refuge_id")
    private Refuge refuge;

    @ManyToOne
    @JoinColumn(name = "adopt_id")
    private Adopter adopt;

}
