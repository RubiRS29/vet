package com.vet.web.app.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vet.web.app.entity.dto.UserDto;
import com.vet.web.app.exceptions.BadRequestException;
import com.vet.web.app.repository.UserRepository;
import com.vet.web.app.response.Response;
import com.vet.web.app.response.ResponseHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static com.vet.web.app.util.Utils.validPassword;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


/*
    1.- First to all, make the register form - just

 */

@Service
public class LoginService implements IAuthenticateUser, UserDetailsService {

    private final UserRepository userRepository;
    private final ResponseHandler responseHandler;

    private final Logger logger = LogManager.getLogger(LoginService.class);
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public LoginService(UserRepository userRepository,
                        ResponseHandler responseHandler, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.responseHandler = responseHandler;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /*
                                We have to make sure what kind of user is,
                                e.i. veterinarian, adopter or refuge


            @Override                 */
    public ResponseEntity<Response> signup( UserDto user ) {

        logger.info("Checking user's data");

        if (!userRepository.isUniqueEmail(user.getEmail())) {
            logger.info(String.format("Email %s has been registered", user.getEmail()));
            throw new BadRequestException(String.format("Email %s has been registered", user.getEmail()));
        }

        if (!validPassword(user.getPassword())) {
            throw new BadRequestException("Password is not valid");
        }


        if( !StringUtils.hasText(user.getEmail()) ||  !StringUtils.hasText(user.getFirstName()) ||
            !StringUtils.hasText(user.getLastName())|| !StringUtils.hasText(user.getPassword()) ) {
            logger.info("Some data has been missing");
            throw new BadRequestException("Some data has been missing");
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        return responseHandler.successResponse(String.format("User %s has been successfully created", user.getFirstName()));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<UserDto> userDto = userRepository.findUserByEmail(email);

        if (userDto.isEmpty()) {
            logger.error(String.format("User's email %s is not found", email));
            throw new UsernameNotFoundException((String.format("User's email %s is not found", email)));
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userDto.get().getRoles().name()));

        return new User(userDto.get().getEmail(), userDto.get().getPassword(), authorities);
    }

    @Override
    public void refreshToken(HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
        String header = request.getHeader(AUTHORIZATION);

        if (header != null && header.startsWith("Bearer ")) {

            try {
                String refresh_token = header.substring("Bearer ".length());

                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decoder = verifier.verify(refresh_token);
                String email = decoder.getSubject();

                UserDto user = userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user not found"));


                String access_token = JWT.create()
                        .withSubject(user.getEmail())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 6 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", Collections.singletonList(user.getRoles()))
                        .sign(algorithm);

                Map<String, String> tokens = new HashMap<>();

                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);


                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);


            } catch (Exception e) {

                logger.info("Error login in {}", e.getMessage());
                response.setHeader("error", e.getMessage());

                Map<String, String> error = new HashMap<String, String>();

                error.put("error_message", e.getMessage());

                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }else {
            throw new RuntimeException("refresh_token is missing");
        }
    }
}
