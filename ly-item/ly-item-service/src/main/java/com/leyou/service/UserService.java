package com.leyou.service;

import com.leyou.mapper.UserMapper;
import com.leyou.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author Administrator
 * @Date 2020/7/21 16:37
 */
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public List<User> QueryAllUsers(){
        return userMapper.selectAll();
    }
}
