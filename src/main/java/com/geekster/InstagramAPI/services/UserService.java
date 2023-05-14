package com.geekster.InstagramAPI.services;


import com.geekster.InstagramAPI.dto.SignInInput;
import com.geekster.InstagramAPI.dto.SignInOutput;
import com.geekster.InstagramAPI.dto.SignUpInput;
import com.geekster.InstagramAPI.dto.SignUpOutput;
import com.geekster.InstagramAPI.model.AuthenticationToken;
import com.geekster.InstagramAPI.model.User;
import com.geekster.InstagramAPI.repositories.IUserRepo;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserService {

    @Autowired
    IUserRepo userRepo;

    @Autowired
    AuthenticationService authenticationService;

    public SignUpOutput signUp(SignUpInput signUpInput) {
        User userA = userRepo.findFirstByEmail(signUpInput.getEmail());

        if(userA != null){
            throw new IllegalStateException("Patient already exists by this details..");
        }

        //encryption
        String encryptedPassword = null;
        try{
            encryptedPassword = encryptPassword(signUpInput.getUserPassword());
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }

        //save the user
        User user = new User(signUpInput.getFirstName(),signUpInput.getLastName(),signUpInput.getAge(),signUpInput.getEmail(),encryptedPassword,signUpInput.getPhoneNumber());

        userRepo.save(user);

        AuthenticationToken token = new AuthenticationToken(user);
        authenticationService.saveToken(token);
        return new SignUpOutput(HttpStatus.ACCEPTED,"User registered Successfully");
    }

    private String encryptPassword(String userPassword) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(userPassword.getBytes());
        byte[] digested =  md5.digest();

        String hash = DatatypeConverter.printHexBinary(digested);
        return hash;
    }

    public SignInOutput signIn(SignInInput signInInput){
        User user = userRepo.findFirstByEmail(signInInput.getEmail());
        if(user == null){
            throw new IllegalStateException("User invalid..!!!");
        }
        //encrypt the password
        String encryptedPassword = null;
        try{
            encryptedPassword = encryptPassword(signInInput.getPassword());
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        //match it with database encryption
        boolean isPasswordValid = encryptedPassword.equals(user.getPassword());
        if(!isPasswordValid){
            throw new IllegalStateException("User invalid..!!!!!!!!");
        }
        AuthenticationToken token = authenticationService.getToken(user);
        return new SignInOutput(HttpStatus.OK,token.getToken());

    }

    public void updateUser(SignUpInput signUpInput) {
        User user1 = userRepo.findFirstByEmail(signUpInput.getEmail());
        if(user1 == null){
            throw new IllegalStateException("User invalid..!!!");
        }
        String encryptedPassword = null;
        if(signUpInput.getEmail() != null)
        {
            try {
                encryptedPassword = encryptPassword(signUpInput.getUserPassword());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        //save the user
        User user = new User(user1.getUserId(),signUpInput.getFirstName(),signUpInput.getLastName(),signUpInput.getAge(),signUpInput.getEmail(),encryptedPassword,signUpInput.getPhoneNumber());

        userRepo.save(user);
    }
}
