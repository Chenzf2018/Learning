package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Chenzf
 */

@Controller
public class IndexController {

    @GetMapping("/index")
    public String toLogin() {
        return "/login";
    }

    @GetMapping("/toRegisterController")
    public String toRegisterController() {
        return "/register";
    }

    @GetMapping("/AddEmployee")
    public String addEmployee() {
        return "/addEmployee";
    }
}
