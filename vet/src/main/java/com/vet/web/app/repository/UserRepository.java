package com.vet.web.app.repository;

import com.vet.web.app.entity.Adopter;
import com.vet.web.app.entity.Refuge;
import com.vet.web.app.entity.Veterinarian;
import com.vet.web.app.entity.dto.UserDto;
import com.vet.web.app.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class UserRepository implements IUserRepository{

    private final AdopterRepository adopterRepo;
    private final VeterinarianRepository veterinarianRepo;
    private final RefugeRepository refugeRepo;
    private final UserMapper mapper;


    public UserRepository(AdopterRepository adopterRepo,
                          VeterinarianRepository veterinarianRepo,
                          RefugeRepository refugeRepo,
                          UserMapper mapper) {
        this.adopterRepo = adopterRepo;
        this.veterinarianRepo = veterinarianRepo;
        this.refugeRepo = refugeRepo;
        this.mapper = mapper;
    }

    @Override
    public void save(UserDto user) {

        switch ( user.getRoles() ){
            case ADOPTER:
                Adopter adopter = mapper.toAdopter(user);
                adopterRepo.save(adopter);
            case VETERINARIAN:
                Veterinarian veterinarian = mapper.toVeterinarian(user);
                veterinarianRepo.save(veterinarian);
            case REFUGE:
                Refuge refuge = mapper.toRefuge(user);
                refugeRepo.save(refuge);

        }

    }

    @Override
    public boolean isUniqueEmail(String email) {
        if(adopterRepo.countByEmail(email) != 0
                || veterinarianRepo.countByEmail(email) != 0
                || refugeRepo.countByEmail(email) != 0){

            return false;
        }

        return true;
    }

    @Override
    public Optional<UserDto> findUserByEmail(String email) {
        Optional<Adopter> adopter = adopterRepo.findByEmail(email);
        Optional<Veterinarian> veterinarian = veterinarianRepo.findByEmail(email);
        Optional<Refuge> refuge = refugeRepo.findByEmail(email);


        if (adopter.isPresent()){
            return Optional.of(mapper.toUserDto(adopter.get()));
        }else if (veterinarian.isPresent()){
            return Optional.of(mapper.toUserDto(veterinarian.get()));
        }else if (refuge.isPresent()) {
            return Optional.of(mapper.toUserDto(refuge.get()));
        }
        return Optional.empty();
    }

}
