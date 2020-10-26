package com.example.service;

import com.example.entity.User;

/**
 * @author Chenzf
 * @date 2020/10/25
 */

public interface UserService {
    /**
     * 注册用户
     * @param user 待注册用户
     */
    void register(User user);

    /**
     * 登录功能
     * @param username 用户名
     * @param userpassword 用户密码
     */
    User login(String username, String userpassword);
}
