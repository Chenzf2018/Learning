package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import com.example.utils.ValidateImageCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author Chenzf
 */

@Controller
@RequestMapping("/UserController")

public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录功能
     * @param username 用户名
     * @param userpassword 用户密码
     * @return
     */
    @PostMapping("/login")
    public String login(String username, String userpassword) {
        User user = userService.login(username, userpassword);
        if (user != null) {
            // 跳转至员工列表
            return "redirect:/EmployeeController/findAllEmployee";
        } else {
            // 跳转至登录页面
            return "redirect:/index";
        }
    }

    /**
     * 用户注册
     * @param user 待注册用户
     * @return 跳转至登录页面或注册页面
     */
    @PostMapping("/register")
    public String register(User user, String validateCode, HttpSession session) {
        String sessionCode = (String) session.getAttribute("validateCode");
        if (sessionCode.equalsIgnoreCase(validateCode)) {
            userService.register(user);
            // 注册成功，跳转至登录页面
            return "redirect:/index";
        } else {
            // 注册失败，跳转至注册页面
            return "redirect:/toRegisterController";
        }
    }

    @GetMapping("/validateCode")
    public void getImage(HttpSession session, HttpServletResponse response) throws IOException {
        // 生成验证码
        String validateCode = ValidateImageCodeUtils.getSecurityCode();
        BufferedImage image = ValidateImageCodeUtils.createImage(validateCode);
        // 存入session作用域
        session.setAttribute("validateCode", validateCode);
        // 响应图片
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, "png", outputStream);
    }
}