package com.df.controller;

import com.df.pojo.User;
import com.df.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Loser
 * @date 2021年11月09日 17:11
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 添加用户
     * @param user
     * @return
     */
    @RequestMapping("/addUser")
    public String addUser(User user){
        userService.addUser(user);
        return "redirect:/user/findUsers";
    }
    @RequestMapping("/add")
    public String add(){
        return "add";
    }

    /**
     * 查询所有用户
     * @return
     */
    @RequestMapping("/findUsers")
    public String findUsers(Model model){
        List<User> list = userService.findUsers();
        model.addAttribute("list",list);
        return "list";
    }

    /**
     * 根据用户id删除用户
     * @param id
     * @return
     */
    @RequestMapping("/delUser")
    public String delUserById(Integer id){
        userService.delUserById(id);
        return "redirect:/user/findUsers";
    }

    /**
     * 根据id修改用户信息
     * @param user
     * @return
     */
    @RequestMapping("/editUser")
    public String editUserById(User user){
        userService.editUserById(user);
        return "redirect:/user/findUsers";
    }

    /**
     * 根据id查询用户信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/findUserIById")
    public String findUserIById(Integer id,Model model){
        User user = userService.findUserIById(id);
        model.addAttribute("User",user);
        return "user";
    }
    @RequestMapping("/loginUser")
    public String loginUser(){
        return "login";
    }
    @RequestMapping("/login")
    public String login(HttpSession session,User user){
        User uu = userService.login(user);
        if (uu != null){
            //登陆成功
            session.setAttribute("user", user);
            return "redirect:/user/findUsers";
        }
        return "login";
    }
}
