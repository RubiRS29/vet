package com.vet.web.app.entity.dto;

import com.vet.web.app.entity.TypeOfUser;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private TypeOfUser type;

    private LocalDate created;
    private LocalDate updated;

}
