package com.instagram.server.model;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InstagramPost {
    private String postId;
    private String title;
    private String comment;
    private String image;
    private Date date; 
}
