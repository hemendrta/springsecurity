package com.spring.docker.controller;

import com.spring.docker.entity.JWTRequest;
import com.spring.docker.entity.JWTResponse;
import com.spring.docker.service.UserService;
import com.spring.docker.utilties.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecureControllerDemo {

    //utility class where we have all the information for creating and managing the JWT tokens.

    @Autowired
    private JWTUtility jwtUtility;

    // This class is used for the usage of in-built methods for authentication purpose

    @Autowired
    private AuthenticationManager authenticationManager;

    //UserService class implements the inbuilt UserDetailService which has method loadByUsername(String username), this method will help us in getting the user by username.

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String getTheMessage(){

        return "This is secured api section!!!";

    }

    @PostMapping("/authenticate")
    public JWTResponse authenticate(@RequestBody JWTRequest jwtRequest) throws Exception {

        //code for checking the username and password (authentication) by using the inbuilt class method authenticate.

        try {
            authenticationManager.authenticate(

                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )

            );
        }
        catch (BadCredentialsException e){

           throw new Exception("INVALID_CREDENTIALS", e);

        }

        //code to create the jwt token once the authentication is done above

        //fetching the userdetails using the service.

        final UserDetails userDetails=
                    userService.loadUserByUsername(jwtRequest.getUsername());

        //generating the token using the utility method from jwtutility class.

        final String token =
                jwtUtility.generateToken(userDetails);

        return new JWTResponse(token);
    }

}
