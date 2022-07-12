package com.vet.web.app.entity.dto;

import com.sun.istack.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserAuth {
    @NotNull
    private String email;
    @NotNull
    private String password;

}
