package com.geekster.InstagramAPI.controllers;

import com.geekster.InstagramAPI.dto.SignInInput;
import com.geekster.InstagramAPI.dto.SignInOutput;
import com.geekster.InstagramAPI.dto.SignUpInput;
import com.geekster.InstagramAPI.dto.SignUpOutput;
import com.geekster.InstagramAPI.services.AuthenticationService;
import com.geekster.InstagramAPI.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/signUp")
    public SignUpOutput signup(@RequestBody SignUpInput signUpInput){
        return userService.signUp(signUpInput);
    }
    @PostMapping("/signIn")
    public SignInOutput signIn(@RequestBody SignInInput signInInput){
        return userService.signIn(signInInput);


    }
}
