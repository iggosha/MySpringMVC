package com.myspring.controllers;

import com.myspring.models.Author;
import com.myspring.models.Device;
import com.myspring.models.NewsCard;
import com.myspring.repositories.AuthorsRepository;
import com.myspring.repositories.DevicesRepository;
import com.myspring.repositories.NewsCardsRepository;
import com.myspring.utils.DeviceValidator;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/smarthome")
public class AdminController {

    private static final String CURR_HTML = "/smart_home/admin";
    private static final String REDIRECT_CURR_URL = "redirect:/smarthome/admin";


    private DevicesRepository devicesRepository;
    private DeviceValidator deviceValidator;
    private NewsCardsRepository newsCardsRepository;
    private AuthorsRepository authorsRepository;

    @PostMapping({"/admin/device", "/admin/device/"})
    public String postDevice(@ModelAttribute("device") @Valid Device device,
                             BindingResult bindingResult) {
        deviceValidator.validate(device, bindingResult);
        if (bindingResult.hasErrors()) {
            return CURR_HTML;
        }
        devicesRepository.save(device);
        return REDIRECT_CURR_URL;
    }

    @PostMapping({"/admin/newscard", "/admin/newscard/"})
    public String postNewscard(@ModelAttribute("newscard") @Valid NewsCard newsCard,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return CURR_HTML;
        }
        newsCardsRepository.save(newsCard);
        return REDIRECT_CURR_URL;
    }

    @PostMapping({"/admin/author", "/admin/author/"})
    public String postAuthor(@ModelAttribute("author") Author author,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return CURR_HTML;
        }
        authorsRepository.save(author);
        return REDIRECT_CURR_URL;
    }

    @GetMapping({"/admin", "/admin/"})
    public String getAdmin(@ModelAttribute("device") Device device,
                           @ModelAttribute("newscard") NewsCard newsCard,
                           @ModelAttribute("author") Author author, Model model) {
        model.addAttribute("authors", authorsRepository.findAll());
        return "smart_home/admin";
    }
}
