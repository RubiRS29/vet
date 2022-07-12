package com.vet.web.app.repository;


import com.vet.web.app.entity.dto.UserDto;

import java.util.Optional;

public interface IUserRepository {

    void save(UserDto user);

    boolean isUniqueEmail(String email);

    Optional<UserDto> findUserByEmail(String email);

}
