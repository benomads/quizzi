package com.benomads.quizzi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

//@Data
@Getter
@Setter
@Entity(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;
//    private String dateOfBirth;
    private Character gender;
    private LocalDateTime createdAt;

    public User(
                String firstName,
                String lastName,
                String email,
                String password,
//                Date dateOfBirth,
                Character gender
                ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
//        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.createdAt = LocalDateTime.now();
    }

    public User() {

    }

    //    private List<User> friends; //TODO what we need choose here?

//    private String role;
//    private String avatar;
//    private String accessLevel;
//    private String aboutMe; //description???



}
