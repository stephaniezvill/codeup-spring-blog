package com.codeup.codeupspringblog.models;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 60)
    private String title;

    @Column(nullable = false, length = 500)
    private String body;

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }
}