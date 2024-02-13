package com.myspring.controllers;

import com.myspring.services.AuthorsService;
import com.myspring.services.DevicesService;
import com.myspring.services.NewsCardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("/smarthome")
public class SmartHomeController {

    private DevicesService devicesService;
    private NewsCardsService newsCardsService;
    private AuthorsService authorsService;

    @GetMapping({"", "/"})
    public String getStart(Model model) {
        model.addAttribute("devices", devicesService.findMultipleNewest(4));
        model.addAttribute("newscards", newsCardsService.findMultipleNewest(4));
        Map<Long, String> authorMap = new HashMap<>();
        authorsService.findAll()
                .forEach(author ->
                        authorMap.put(author.getId(), author.getFullName())
                );
        model.addAttribute("authorMap", authorMap);
        return "smart_home/start_page";
    }
}
