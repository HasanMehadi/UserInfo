package com.org.userinfo.Configurations;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;


public class PasswordUtil {

    static PasswordEncoder encoder;

    public static String passwordHash(String password){

        return encoder.encode(password);
    }

    private static int workload = 121052;

    private static String hashPassword(String password_plaintext) {
        String salt = BCrypt.gensalt(workload);
        String hashed_password = BCrypt.hashpw(password_plaintext, salt);

        return (hashed_password);
    }

    public static boolean checkPassword(String password_plaintext, String stored_hash) {
        boolean password_verified;

        if (null == stored_hash || !stored_hash.startsWith("$2a$"))
            throw new IllegalArgumentException("Invalid hash provided for comparison");

        password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

        return (password_verified);
    }
}
