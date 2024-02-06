package com.myspring.controllers;

import com.myspring.models.Author;
import com.myspring.models.Device;
import com.myspring.models.NewsCard;
import com.myspring.repositories.AuthorsRepository;
import com.myspring.repositories.DevicesRepository;
import com.myspring.repositories.NewsCardsRepository;
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
@RequestMapping("/smarthome")
public class AdminController {

    private static final String REDIRECT_ADMIN = "redirect:/smarthome/admin";


    private DevicesRepository devicesRepository;
    private NewsCardsRepository newsCardsRepository;
    private AuthorsRepository authorsRepository;

    @PostMapping({"/admin/manage/device", "/admin/manage/device/"})
    public String postDevice(@ModelAttribute("device") @Valid Device device) {
        devicesRepository.save(device);
        return REDIRECT_ADMIN;
    }

    @PostMapping({"/admin/manage/newscard", "/admin/manage/newscard/"})
    public String postNewscard(@ModelAttribute("newscard") @Valid NewsCard newsCard) {
        newsCardsRepository.save(newsCard);
        return REDIRECT_ADMIN;
    }

    @PostMapping({"/admin/manage/author", "/admin/manage/author/"})
    public String postAuthor(@ModelAttribute("author") Author author) {
        authorsRepository.save(author);
        return REDIRECT_ADMIN;
    }

    @GetMapping({"/admin/manage/author", "/admin/manage/author"})
    public String getManageAuthor(@ModelAttribute("author") Author author) {
        return "smart_home/manage_author";
    }

    @GetMapping({"/admin/manage/newscard", "/admin/manage/newscard/"})
    public String getManageNewsCard(@ModelAttribute("newscard") NewsCard newsCard,
                                    Model model) {
        model.addAttribute("authors", authorsRepository.findAll());
        return "smart_home/manage_newscard";
    }

    @GetMapping({"/admin/manage/device", "/admin/manage/device/"})
    public String getManageDevice(@ModelAttribute("device") Device device) {
        return "smart_home/manage_device";
    }
    @GetMapping({"/admin", "/admin/"})
    public String getAdmin() {
        return "smart_home/admin";
    }
}
