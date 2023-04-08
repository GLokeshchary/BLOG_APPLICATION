package com.microservice.blogappapis.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;
    private String commentContent;
    @ManyToOne
    @JoinColumn(name = "post_Id")
    private Post post;
    @ManyToOne
    @JoinColumn(name = "user_Id")
    private User user;
}
