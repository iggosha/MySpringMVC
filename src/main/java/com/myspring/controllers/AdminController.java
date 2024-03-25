package com.myspring.controllers;

import com.myspring.models.Author;
import com.myspring.models.Device;
import com.myspring.models.NewsCard;
import com.myspring.services.AuthorsService;
import com.myspring.services.DevicesService;
import com.myspring.services.NewsCardsService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/smarthome/admin")
public class AdminController {

    private static final String REDIRECT_ADMIN = "redirect:/smarthome/admin";

    private DevicesService devicesService;
    private NewsCardsService newsCardsService;
    private AuthorsService authorsService;

    @PostMapping({"/manage/device", "/manage/device/"})
    public String postDevice(@ModelAttribute("device") @Valid Device device) {
        devicesService.save(device);
        return REDIRECT_ADMIN;
    }

    @PostMapping({"/manage/newscard", "/manage/newscard/"})
    public String postNewscard(@ModelAttribute("newscard") @Valid NewsCard newsCard) {
        newsCardsService.save(newsCard);
        return REDIRECT_ADMIN;
    }

    @PostMapping({"/manage/author", "/manage/author/"})
    public String postAuthor(@ModelAttribute("author") Author author) {
        authorsService.save(author);
        return REDIRECT_ADMIN;
    }

    @GetMapping({"/manage/author", "/manage/author"})
    public String getManageAuthor(@ModelAttribute("author") Author author) {
        return "smart_home/manage_author";
    }

    @GetMapping({"/manage/newscard", "/manage/newscard/"})
    public String getManageNewsCard(@ModelAttribute("newscard") NewsCard newsCard,
                                    Model model) {
        model.addAttribute("authors", authorsService.findAll());
        return "smart_home/manage_newscard";
    }

    @GetMapping({"/manage/device", "/manage/device/"})
    public String getManageDevice(@ModelAttribute("device") Device device) {
        return "smart_home/manage_device";
    }

    @GetMapping({"", "/"})
    public String getAdmin() {
        return "smart_home/admin";
    }
}
