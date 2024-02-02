package com.myspring.controllers;

import com.myspring.models.Device;
import com.myspring.models.NewsCard;
import com.myspring.repositories.DevicesRepository;
import com.myspring.repositories.NewsCardsRepository;
import com.myspring.utils.DeviceValidator;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/smarthome")
public class AdminController {


    private DevicesRepository devicesRepository;
    private NewsCardsRepository newsCardsRepository;
    private DeviceValidator deviceValidator;

    @PostMapping({"/admin/device", "/admin/device/"})
    public String postDevice(@ModelAttribute("device") @Valid Device device,
                             BindingResult bindingResultDevice) {
        deviceValidator.validate(device, bindingResultDevice);
        if (bindingResultDevice.hasErrors()) {
            return "/smart_home/admin";
        }
        devicesRepository.save(device);
        return "redirect:/smarthome/admin";
    }

    @PostMapping({"/admin/newscard", "/admin/newscard/"})
    public String postNewscard(@ModelAttribute("newscard") @Valid NewsCard newsCard,
                               BindingResult bindingResultNewsCard) {
        if (bindingResultNewsCard.hasErrors()) {
            return "/smart_home/admin";
        }
        newsCardsRepository.save(newsCard);
        return "redirect:/smarthome/admin";
    }

    @GetMapping({"/admin", "/admin/"})
    public String getAdmin(@ModelAttribute("device") Device device, @ModelAttribute("newscard") NewsCard newsCard) {
        return "smart_home/admin";
    }
}
