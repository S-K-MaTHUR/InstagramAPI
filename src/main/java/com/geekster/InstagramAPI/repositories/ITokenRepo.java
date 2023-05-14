package com.geekster.InstagramAPI.repositories;

import com.geekster.InstagramAPI.model.AuthenticationToken;
import com.geekster.InstagramAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITokenRepo extends JpaRepository<AuthenticationToken,Long> {

    AuthenticationToken findByUser(User user);

    AuthenticationToken findFirstByToken(String token);
}
