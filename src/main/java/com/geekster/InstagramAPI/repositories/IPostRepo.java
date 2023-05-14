package com.geekster.InstagramAPI.repositories;

import com.geekster.InstagramAPI.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostRepo extends JpaRepository<Post,Long> {
}
