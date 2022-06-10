package com.vet.web.app.mapper;


import com.vet.web.app.entity.Refuge;
import com.vet.web.app.entity.dto.UserDto;
import com.vet.web.app.entity.Adopter;
import com.vet.web.app.entity.Veterinarian;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/*
    target belongs to entity
    source to dto
 */

@Mapper(
        componentModel = "spring"
)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    @Mapping(target="firstName", source="firstName")
    @Mapping(target="lastName", source="lastName")
    @Mapping(target="email", source="lastName")
    @Mapping(target="password", source="password")
    @Mapping(target="created", source="created")
    @Mapping(target="updated", source="updated")
    UserDto toUserDto(Veterinarian Veterinarian);

    @InheritInverseConfiguration
    Veterinarian toVeterinarian(UserDto userDto);

    @Mapping(target="firstName", source="firstName")
    @Mapping(target="lastName", source="lastName")
    @Mapping(target="email", source="lastName")
    @Mapping(target="password", source="password")
    @Mapping(target="created", source="created")
    @Mapping(target="updated", source="updated")
    UserDto toUserDto(Adopter adopter);

    @InheritInverseConfiguration
    Adopter toAdopter(UserDto userDto);

    @Mapping(target="firstName", source="firstName")
    @Mapping(target="lastName", source="lastName")
    @Mapping(target="email", source="lastName")
    @Mapping(target="password", source="password")
    @Mapping(target="created", source="created")
    @Mapping(target="updated", source="updated")
    UserDto toUserDto(Refuge refuge);

    @InheritInverseConfiguration
    Refuge toRefuge(UserDto userDto);

}
