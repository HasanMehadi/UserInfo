package com.org.userinfo.Configurations;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



public class PasswordUtil {

    static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(15);


    public static String passwordHash(String password){

        return encoder.encode(password);
    }
}
