package com.org.userinfo.Services;



import com.org.userinfo.DTO.ForgetPasswordDTO;
import com.org.userinfo.Models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



import java.util.List;

public interface UserService {

    public User save(User user);
    public Page<User> findAll(Pageable pageable);
    public User getUserByEmail(String email);
    public User getUserById(Long id);
    public User changePassword(Long id, ForgetPasswordDTO forgetPasswordDTO);
    public User getUserByUserName(String username);
}
