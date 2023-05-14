package com.geekster.InstagramAPI.services;

import com.geekster.InstagramAPI.model.Post;
import com.geekster.InstagramAPI.repositories.IPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    IPostRepo postRepo;

    public List<Post> getAllPost(){
        return postRepo.findAll();
    }
    public Post savePost(Post post){
        return postRepo.save(post);
    }
}
