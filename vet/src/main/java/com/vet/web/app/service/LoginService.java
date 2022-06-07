package com.vet.web.app.service;


import com.vet.web.app.entity.dto.UserDto;
import com.vet.web.app.exceptions.BadRequestException;
import com.vet.web.app.repository.AdopterRepository;
import com.vet.web.app.repository.RefugeRepository;
import com.vet.web.app.repository.VeterinarianRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


/*
    1.- First to all, make the register form - just

 */

@Service
public class LoginService {


    private final AdopterRepository adopter;
    private final VeterinarianRepository veterinarian;
    private final RefugeRepository refuge;

    public LoginService(AdopterRepository adopter,
                        VeterinarianRepository veterinarian,
                        RefugeRepository refuge) {

        this.adopter = adopter;
        this.veterinarian = veterinarian;
        this.refuge = refuge;
    }

    /*
            We have to make sure what kind of user is,
            e.i. veterinarian, adopter or refuge
         */
    public ResponseEntity<String> register(UserDto user) {

        if(adopter.countAdopter(user.getEmail()) > 0 ||
                refuge.countRefuge(user.getEmail()) > 0 ||
                veterinarian.countVeterinarian(user.getEmail()) > 0
        )   {
            throw new BadRequestException(String.format("Email %s has been registered", user.getEmail()));
        }

        return null;
    }


}
