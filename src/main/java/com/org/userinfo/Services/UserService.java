package com.org.userinfo.Services;

import com.org.userinfo.DTO.ForgetPasswordDTO;
import com.org.userinfo.Models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

     User save(User user);
     Page<User> findAll(Pageable pageable);
     User getUserByEmail(String email);
     User getUserByUserName(String username);
     Boolean changePassword(ForgetPasswordDTO forgetPasswordDTO);
     void changePasswordConfirm(String password);
}
