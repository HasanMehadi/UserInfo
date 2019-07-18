package com.org.userinfo.Configurations;


import com.org.userinfo.Models.User;
import com.org.userinfo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsernameIgnoreCase(username);

        if(user == null){
            throw new UsernameNotFoundException("No user found with user name "+ username+" .");
        }else {
           return JwtUserFactory.createUser(user);
        }
    }
}
