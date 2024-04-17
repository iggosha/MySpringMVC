package com.sportline.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("sportline")
public class BasicController {

    @GetMapping("")
    public String getHomePage(Model model) {

        return "sportline/basic/home";
    }
}
