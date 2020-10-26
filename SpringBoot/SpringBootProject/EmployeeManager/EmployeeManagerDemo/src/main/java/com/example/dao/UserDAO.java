package com.example.dao;

import com.example.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author Chenzf
 * @date 2020/10/25
 */

public interface UserDAO {
    /**
     * 保存用户
     * @param user 待保存用户
     */
    void saveUser(User user);

    // 在mybatis中传递多个参数，需要参数绑定
    User login(@Param("username") String username, @Param("userpassword") String userpassword);
}