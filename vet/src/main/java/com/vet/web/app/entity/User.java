package com.vet.web.app.entity;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@MappedSuperclass
@Getter @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,
            length = 50)
    private String firstName;

    @Column(length = 50)
    private String lastName;

    @Column(nullable = false,
            length = 80,
            unique = true)
    private String email;

    @Column(nullable = false,
            length = 40)
    private String password;

    @Column(length = 40)
    private State state;

    @Column(length = 80)
    private String address;

    @Column(length = 12)
    private String phoneNumber;

    @CreationTimestamp
    private LocalDate created;

    @UpdateTimestamp
    private LocalDate updated;

}
