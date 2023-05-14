package com.geekster.InstagramAPI.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    private String postData;
    private Timestamp createDate;
    private Timestamp updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}
