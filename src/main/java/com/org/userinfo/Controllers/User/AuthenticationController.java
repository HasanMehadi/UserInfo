package com.org.userinfo.Controllers.User;


import com.org.userinfo.DTO.UserDTO;
import com.org.userinfo.Exceptions.UnAuthorizedException;
import com.org.userinfo.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.org.userinfo.Configurations.JwtTokenUtil;
import com.org.userinfo.Configurations.JwtUser;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthenticationController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping(value = "/login")
    public ResponseEntity<UserDTO> login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response){
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
            final JwtUser userDetails =(JwtUser) authentication.getPrincipal();
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String token = jwtTokenUtil.generateToken(userDetails);
            response.setHeader("Token", token);

            User authenticated = userDetails.getUser();

            return new ResponseEntity<UserDTO>(new UserDTO(authenticated.getId(),authenticated.getFirstName(),
                                                           authenticated.getLastName(), authenticated.getUsername(),
                                                           authenticated.getAddress(),  authenticated.getPhone(),
                                                           authenticated.getEmail(), authenticated.getPassword(),
                                                           authenticated.getDob(),  authenticated.getRole(),
                                                           authenticated.isEnable(), token), HttpStatus.OK);

        }catch (Exception ex){

           throw  new UnAuthorizedException(ex.getMessage());
        }
    }
}
