package com.myspring.controllers;

import com.myspring.repositories.DevicesRepository;
import com.myspring.repositories.NewsCardsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/smarthome")
public class SmartHomeController {

    private DevicesRepository devicesRepository;
    private NewsCardsRepository newsCardsRepository;

    @GetMapping({"", "/"})
    public String getStart(Model model) {
        model.addAttribute("devices", devicesRepository.findBunchOfLastLimited(4));
        model.addAttribute("newscards", newsCardsRepository.findBunchOfLastLimited(4));
        return "smart_home/start";
    }
}
