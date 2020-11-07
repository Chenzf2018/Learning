package com.chenzf.service;

import com.chenzf.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Date;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * @author Chenzf
 *
 * ContextConfiguration：指定Spring的配置文件
 */
@ContextConfiguration("classpath*:/userlogin.xml")
public class UserServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    /**
     * 将Spring容器中的Bean注入测试类中
     */
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 将方法标注为测试方法
     */
    @Test
    public void testHasMatchUser() {
        boolean result1 = userService.hasMatchUser("admin", "123456");
        boolean result2 = userService.hasMatchUser("admin", "123");
        assertTrue(result1);
        assertTrue(result2);
    }

    @Test
    public void testFindUserByUserName() {
        for (int i = 0; i < 100; i++) {
            User user = userService.findUserByUserName("admin");
            assertEquals(user.getUserName(), "admin");
        }
    }

    @Test
    public void testAddLoginLog() {
        User user = userService.findUserByUserName("admin");
        user.setUserId(2);
        user.setUserName("admin");
        user.setLastIp("192.168.12.7");
        user.setLastVisit(new Date());
        userService.loginSuccess(user);
    }
}
