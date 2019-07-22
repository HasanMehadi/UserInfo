package com.org.userinfo.Services;


import com.org.userinfo.Configurations.PasswordUtil;
import com.org.userinfo.Configurations.SecurityUtils;
import com.org.userinfo.DTO.ForgetPasswordDTO;
import com.org.userinfo.Models.User;
import com.org.userinfo.Repositories.UserRepository;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User save(User user) {


        String password = PasswordUtil.passwordHash(user.getPassword());
        user.setEnable(true);
        user.setPassword(password);

        return userRepository.save(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Page<User> findAll(Pageable pageable) {


        return  userRepository.findAll(pageable);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User getUserByEmail(String email) {

        User user= null;
        try{
            user = userRepository.findByEmailIgnoreCase(email);
            if(user == null){
                return null;
            }
        }catch (Exception ex){
            System.out.println(ex.getCause().getMessage());
        }
        return user;
    }


    @Override
    public User getUserByUserName(String username) {
        return null;
    }

    @Override
    @Transactional
    public Boolean changePassword(ForgetPasswordDTO forgetPasswordDTO) {

        boolean status = false;

        String passwordByLogin = userRepository.findPasswordWithOptionalUsernameString(SecurityUtils.getCurrentUserLogin());
        System.out.println(passwordByLogin);

        if(PasswordUtil.checkPassword(forgetPasswordDTO.getOldPassword(),passwordByLogin)){

            changePasswordConfirm(forgetPasswordDTO.getNewPassword());
            status = true;
        }
        return status;
    }

    @Override
    public void changePasswordConfirm(String password) {
        try{
            Optional<String> username = SecurityUtils.getCurrentUserLogin();
            User user = userRepository.findOneByUsername(username);
            userRepository.save(user);

        }catch (Exception ex){
            ex.getCause().getMessage();
        }


    }
}
