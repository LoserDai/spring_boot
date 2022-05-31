package com.df.service;

import com.df.mapper.UserMapper;
import com.df.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Loser
 * @date 2021年11月08日 12:06
 */
@Service
// 事务
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public List<User> findUsers() {
        List<User> list = userMapper.findUsers();
        return list;
    }

    @Override
    public void delUserById(Integer id) {
        userMapper.delUserById(id);
    }

    @Override
    public void editUserById(User user) {
        userMapper.editUserById(user);
    }

    @Override
    public User findUserIById(Integer id) {
        User user = userMapper.findUserIById(id);
        return user;
    }

    @Override
    public User login(User user) {
        User uu = userMapper.login(user);
        return uu;
    }
}
