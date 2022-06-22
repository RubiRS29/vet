package com.vet.web.app.util;

import com.vet.web.app.service.LoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;

import java.util.regex.Pattern;

public class Utils {

    public static boolean validPassword(String password){

        boolean isValid = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z]).{8,}$")
                .matcher(password)
                .find();
        System.out.println(password);

        return isValid;
    }

    /*
        Let's start by defing a simple BycryptPasswordEncoder as a bean in our configuration
     */


}
