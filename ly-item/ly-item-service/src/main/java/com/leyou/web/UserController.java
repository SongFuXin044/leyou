package com.leyou.web;

import com.leyou.pojo.User;
import com.leyou.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author Administrator
 * @Date 2020/7/21 16:38
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping
    public List<User> QueryAllUsers(){
        return userService.QueryAllUsers();
    }
}
