package com.benomads.quizzi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Entity(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String dateOfBirth;

    @Column(nullable = false)
    private Character gender;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;


//    private List<User> friends; TODO what we need choose here?
//
//    private String role;
//    private String avatar;
//    private String accessLevel;
//    private String aboutMe; //description???



}
