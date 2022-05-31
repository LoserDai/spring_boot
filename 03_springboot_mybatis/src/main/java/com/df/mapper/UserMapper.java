package com.df.mapper;
import com.df.pojo.User;

import java.util.List;

/**
 * @author Loser
 * @date 2021年11月08日 12:04
 */
public interface UserMapper {
    /**
     * 添加用户
     * @param user
     */
    void insertUser(User user);

    /**
     * 查找所有用户
     * @return
     */
    List<User> findUsers();

    /**
     * 根据id删除用户
     * @param id
     */
    void delUserById(Integer id);

    /**
     * 根据id修改用户信息
     * @param user
     */
    void editUserById(User user);

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User findUserIById(Integer id);

    User login(User user);
}
