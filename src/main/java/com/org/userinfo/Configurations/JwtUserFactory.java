package com.org.userinfo.Configurations;


import com.org.userinfo.Models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JwtUserFactory {

    public static JwtUser createUser(User user){

        System.out.println("Jwt user Create called");

        return new JwtUser(user.getId() ,user.getUsername(), user.getPassword(),user,maptoGrantedAuthorities(new ArrayList<>(Arrays.asList(user.getRole()))));
    }

    private static List<GrantedAuthority> maptoGrantedAuthorities(List<String> authorities){

        return authorities.stream().map(Authority-> new SimpleGrantedAuthority(Authority)).collect(Collectors.toList());
    }
}
