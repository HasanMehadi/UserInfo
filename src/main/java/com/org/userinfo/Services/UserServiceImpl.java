package com.org.userinfo.Services;


import com.org.userinfo.Configurations.PasswordUtil;
import com.org.userinfo.DTO.ForgetPasswordDTO;
import com.org.userinfo.Models.User;
import com.org.userinfo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

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
    @Transactional(rollbackFor = Exception.class)
    public User getUserById(Long id) {

        User user= null;
        try{
            user = userRepository.getUserById(id);
            if(user == null){
                return null;
            }
        }catch (Exception ex){
            System.out.println(ex.getCause().getMessage());
        }
        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User changePassword(Long id , ForgetPasswordDTO forgetPasswordDTO){
        try{
            User user = userRepository.getUserById(id);

            if (user != null){
                user.setPassword(PasswordUtil.passwordHash(forgetPasswordDTO.getNewPassword()));
                return userRepository.save(user);
            }
        }catch (Exception ex){
            System.out.println(ex.getCause().getMessage());
        }
        return null;
    }

    @Override
    public User getUserByUserName(String username) {
        User user;
        try{
            user = userRepository.findByUsernameIgnoreCase(username);
            if(user == null){
                return null;
            }
        }catch (Exception ex){
            throw new UsernameNotFoundException("No user found with user name "+ username+" .");
        }
        return user;
    }
}
