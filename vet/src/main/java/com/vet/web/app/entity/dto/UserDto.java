package com.vet.web.app.entity.dto;

import com.sun.istack.NotNull;
import com.vet.web.app.entity.Roles;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {


    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    private String password;

    @NotNull
    private Roles roles;

    private LocalDate created;
    private LocalDate updated;

}
