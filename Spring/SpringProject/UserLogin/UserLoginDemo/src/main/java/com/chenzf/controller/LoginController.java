package com.chenzf.controller;

import com.chenzf.entity.User;
import com.chenzf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author Chenzf
 *
 * Controller：标注成为一个Spring MVC的Controller处理HTTP请求
 */
@Controller
public class LoginController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 负贡处理/index的请求
     * @return login
     */
    @RequestMapping(value = "/index")
    public String loginPage() {
        return "login";
    }

    /**
     * 负贡处理/loginCheck的请求
     * @param request 请求
     * @param loginCommand loginCommand
     * @return ModelAndView
     */
    @RequestMapping(value = "/loginCheck")
    public ModelAndView loginCheck(HttpServletRequest request, LoginCommand loginCommand) {
        boolean isValidUser =  userService.hasMatchUser(loginCommand.getUserName(), loginCommand.getPassword());
        if (!isValidUser) {
            return new ModelAndView("login", "error", "用户名或密码错误。");
        } else {
            User user = userService.findUserByUserName(loginCommand.getUserName());
            user.setLastIp(request.getLocalAddr());
            user.setLastVisit(new Date());
            userService.loginSuccess(user);
            request.getSession().setAttribute("user", user);
            return new ModelAndView("main");
        }
    }
}