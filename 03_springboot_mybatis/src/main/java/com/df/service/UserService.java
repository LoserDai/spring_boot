package com.df.service;

import com.df.pojo.User;

import java.util.List;

/**
 * @author Loser
 * @date 2021年11月08日 12:05
 */
public interface UserService {

    void addUser(User user);

    List<User> findUsers();

    void delUserById(Integer id);

    void editUserById(User user);

    User findUserIById(Integer id);

    User login(User user);
}
