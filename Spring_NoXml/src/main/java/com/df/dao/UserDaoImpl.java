package com.df.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Loser
 * @date 2021年11月05日 9:59
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Override
    public void addUser() {
        System.out.println("insert into t_user... ...");
    }
}
