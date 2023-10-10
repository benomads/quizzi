package com.benomads.quizzi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @Size(min = 2, max = 50)
    @NotBlank(message = "First Name is mandatory")
//    @NotNull(message = "Invalid first name: First name is NULL")
    private String firstName;

    @Size(min = 2, max = 50)
    @NotBlank(message = "Last Name is mandatory")
    private String lastName;

    @Column(nullable = false)
    @Email(regexp = ".+[@].+[\\.].+", message = "Example: example@domein.com")
    private String email;

    @Size(min = 6, max = 25, message = "The numbers of symbols should be between 6-25.")
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @NotBlank(message = "Example: 1995-07-25")
    private String dateOfBirth;

    @Column(nullable = false)
//    @NotBlank(message = "Man = 'M' and Woman = 'M'")
    private Character gender;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;




// TODO Mb we need to add fields friends, role, avatar, accessLevel, aboutMe





}
