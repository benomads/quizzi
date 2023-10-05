package com.benomads.quizzi.dao;

import com.benomads.quizzi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    Boolean existsUserByEmail(String email);
}
