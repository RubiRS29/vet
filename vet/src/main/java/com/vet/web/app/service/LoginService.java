package com.vet.web.app.service;


import com.vet.web.app.entity.Adopter;
import com.vet.web.app.entity.Refuge;
import com.vet.web.app.entity.TypeOfUser;
import com.vet.web.app.entity.Veterinarian;
import com.vet.web.app.entity.dto.UserDto;
import com.vet.web.app.exceptions.BadRequestException;
import com.vet.web.app.mapper.UserMapper;
import com.vet.web.app.repository.AdopterRepository;
import com.vet.web.app.repository.RefugeRepository;
import com.vet.web.app.repository.VeterinarianRepository;
import com.vet.web.app.response.Response;
import com.vet.web.app.response.ResponseHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.util.StringUtils;


/*
    1.- First to all, make the register form - just

 */

@Service
public class LoginService {

    private final AdopterRepository adopterRepo;
    private final VeterinarianRepository veterinarianRepo;
    private final RefugeRepository refugeRepo;
    private final UserMapper mapper;
    private final ResponseHandler responseHandler;
    private TypeOfUser type;


    private final Logger logger = LogManager.getLogger(LoginService.class);

    public LoginService(AdopterRepository adopterRepo,
                        VeterinarianRepository veterinarianRepo,
                        RefugeRepository refugeRepo,
                        UserMapper mapper, ResponseHandler responseHandler) {
        this.adopterRepo = adopterRepo;
        this.veterinarianRepo = veterinarianRepo;
        this.refugeRepo = refugeRepo;
        this.mapper = mapper;
        this.responseHandler = responseHandler;
    }

    /*
                    We have to make sure what kind of user is,
                    e.i. veterinarian, adopter or refuge
                 */
    public ResponseEntity<Response> register(UserDto user ) {

        if( justOneEmail( user.getEmail() ) ){
            logger.info(String.format("Email %s has been registered", user.getEmail()));
            throw new BadRequestException(String.format("Email %s has been registered", user.getEmail()));
        }

        logger.info("Checking user's data");

        if( !StringUtils.hasText(user.getEmail()) ||  !StringUtils.hasText(user.getFirstName()) ||
            !StringUtils.hasText(user.getLastName())|| !StringUtils.hasText(user.getPassword()) ) {
            logger.info("Some data has been missing");
            throw new BadRequestException("Some data has been missing");
        }

        switch ( user.getType() ){

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

        return responseHandler.successResponse(String.format("User %s has been successfully created", user.getFirstName()));
    }

    private boolean justOneEmail( String email ){

        return adopterRepo.countAdopter( email ) == 0 ||
                veterinarianRepo.countVeterinarian( email ) == 0 ||
                refugeRepo.countRefuge( email ) == 0;

    }

}
