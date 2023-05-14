package com.geekster.InstagramAPI.services;

import com.geekster.InstagramAPI.model.AuthenticationToken;
import com.geekster.InstagramAPI.model.User;
import com.geekster.InstagramAPI.repositories.ITokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    ITokenRepo tokenRepo;

    public void saveToken(AuthenticationToken token){
        tokenRepo.save(token);
    }
    public AuthenticationToken getToken(User user){
        return tokenRepo.findByUser(user);
    }
    public boolean authenticate(String email,String token){
        AuthenticationToken authenticationToken = tokenRepo.findFirstByToken(token);
        Optional<String> expectedMail = Optional.ofNullable(authenticationToken.getUser().getEmail());
        if(expectedMail.isPresent()){
            return true;
        }
        else {
            return false;
        }
    }
}
