package com.myspring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartController {

    @GetMapping("/hello")
    public String helloPage() {
        return "start/hello";
    }

    @GetMapping("/goodbye")
    public String goodbyePage() {
        return "start/goodbye";
    }
}
