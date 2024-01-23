package com.myspring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StartController {

    @GetMapping("/bank")
    public String helloPage(@RequestParam(name = "bankName") String bankName, Model model) {
        model.addAttribute("bankName", bankName);
        return "start/bank";
    }
}
