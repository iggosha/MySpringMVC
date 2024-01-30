package com.myspring.controllers;

import com.myspring.models.Device;
import com.myspring.repositories.DevicesRepository;
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
    private DeviceValidator deviceValidator;

    @PostMapping({"/admin", "/admin/"})
    public String postDevice(@ModelAttribute("device") @Valid Device device,
                             BindingResult bindingResult) {
        deviceValidator.validate(device, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/smart_home/admin";
        }
        devicesRepository.save(device);
        return "redirect:/smarthome/admin";
    }

    @GetMapping({"/admin", "/admin/"})
    public String getAdmin(@ModelAttribute("device") Device device) {
        return "smart_home/admin";
    }
}
