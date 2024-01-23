package com.myspring.controllers;

import com.myspring.repositories.DevicesDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/smarthome")
public class SmartHomeController {

    private DevicesDAO devicesDAO;

    @GetMapping("")
    public String getHome() {
        return "smart_home/start";
    }

    @GetMapping("/devices")
    public String getDevices(Model model) {
        model.addAttribute("devices", devicesDAO.findAll());
        return "smart_home/devices";
    }

    @GetMapping("/devices/{id}")
    public String getDevice(@PathVariable("id") long id, Model model) {
        model.addAttribute("device", devicesDAO.findById(id));
        return "smart_home/device";
    }
}
