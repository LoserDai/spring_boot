package com.df.service;

import com.df.dao.UserDao;
import com.df.pojo.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Loser
 * @date 2021年11月05日 10:01
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private Cat cat;

    @Override
    public void addUser(){
        userDao.addUser();
        System.out.println(cat);
    }
}
