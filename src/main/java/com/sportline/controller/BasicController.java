package com.sportline.controller;

import com.sportline.security.UserDetailsWrapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("sportline")
public class BasicController {

    @GetMapping("")
    public String getHomePage() {
        return "sportline/basic/home";
    }

    @GetMapping("/userInfo")
    public String getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsWrapper userDetailsWrapper = (UserDetailsWrapper) authentication.getPrincipal();
        System.out.println(userDetailsWrapper.getUser());
        return "sportline/basic/home";
    }
}