package com.benomads.quizzi.service;

import com.benomads.quizzi.dao.UserDao;
import com.benomads.quizzi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserDao userDao;


    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }


    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public User createUser(User user) {
        String userEmail = user.getEmail();

        if (!userDao.existsUserByEmail(userEmail))
            throw new IllegalStateException(
                String.format(
                    "the User with same email: %s is exists.", userEmail));

        return userDao.save(user);
    }


}
