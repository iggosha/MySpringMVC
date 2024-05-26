package com.sportline.controller;

import com.sportline.model.entity.User;
import com.sportline.security.UserDetailsWrapper;
import com.sportline.service.UserService;
import com.sportline.util.UserValidator;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("sportline")
public class BasicController {

    private static final String REGISTRATION_PAGE = "sportline/basic/registration";
    private static final String LOGIN_PAGE = "sportline/basic/login";

    private final UserValidator userValidator;
    private final UserService userService;

    @GetMapping("")
    public String getHomePage() {
        return "sportline/basic/home";
    }

    @GetMapping("/login")
    public String getLogin(Authentication authentication, Model model) {
        if (authentication != null) {
            UserDetailsWrapper userDetailsWrapper = (UserDetailsWrapper) authentication.getPrincipal();
            model.addAttribute("userDetailsWrapper", userDetailsWrapper);
        }
        return LOGIN_PAGE;
    }

    @GetMapping("/registration")
    public String getRegistration(@ModelAttribute("user") User user) {
        return REGISTRATION_PAGE;
    }

    @GetMapping("/admin")
    public String getAdmin() {
        return "sportline/basic/admin";
    }

    @PostMapping("/registration")
    public String postRegistration(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return REGISTRATION_PAGE;
        }
        userService.create(user);
        return "redirect:/sportline/basic/login";
    }
}