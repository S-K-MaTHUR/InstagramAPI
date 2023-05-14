package com.geekster.InstagramAPI.repositories;

import com.geekster.InstagramAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User,Long> {

    Boolean existsByEmail(String email);

    User findFirstByEmail(String userEmail);
}
