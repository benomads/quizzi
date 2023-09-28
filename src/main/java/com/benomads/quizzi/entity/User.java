package com.benomads.quizzi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
//@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date dateOfBirth;
    private Character gender;
    private LocalDateTime createdAt;

    //private List<User> friends; //TODO what we need choose here?
    private String avatar;
    private String role;
    private String accessLevel;
    private String aboutMe; //description???



}
