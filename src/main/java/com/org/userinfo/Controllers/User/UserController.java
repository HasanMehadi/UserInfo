package com.org.userinfo.Controllers.User;


import com.org.userinfo.DTO.ForgetPasswordDTO;
import com.org.userinfo.DTO.UserDTO;
import com.org.userinfo.Models.User;
import com.org.userinfo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.org.userinfo.Configurations.ModelEntityConversionUtil;
import com.org.userinfo.Configurations.Response;


import java.security.Principal;


@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("getUser")
    @PreAuthorize("hasAnyRole('ADMIN') or hasAnyRole('USER')")
    public ResponseEntity<User> getUser(Principal principal) {

        User user = userService.getUserByEmail(principal.getName());

        return new ResponseEntity<>(user, HttpStatus.OK);

    }


    @PostMapping("updatePassword")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<Response> updatePassword(Long id , ForgetPasswordDTO forgetPasswordDTO) {

        try{
            if(userService.changePassword(id,forgetPasswordDTO) != null){
                return new ResponseEntity<>(new Response("true"),HttpStatus.OK);
            }
        }catch (Exception ex){
            System.out.println(ex.getCause().getMessage());
        }
        return new ResponseEntity<>(new Response("Forbid to Change Password, Contract with Provider "),HttpStatus.OK);
    }

    @GetMapping("users")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Page<UserDTO>> getUserByPage(Pageable pageable) {

        try{
            return new ResponseEntity<>(ModelEntityConversionUtil.convertPage(userService.findAll(pageable),UserDTO.class),HttpStatus.OK);
        }catch (Exception ex){
            return ResponseEntity.noContent().header(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS.getReasonPhrase(),HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS.getReasonPhrase()).build();
        }

    }
}
