package com.myspring.controllers;

import com.myspring.models.Device;
import com.myspring.repositories.DevicesDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/smarthome")
public class SmartHomeController {

    private DevicesDAO devicesDAO;

    @GetMapping()
    public String getStart(@ModelAttribute("device") Device device) {
        return "smart_home/start";
    }

    @PostMapping()
    public String postDevice(@ModelAttribute("device") Device device) {
        devicesDAO.save(device);
        return "redirect:/smarthome";
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

    @PatchMapping("/devices/{id}")
    public String patchDevice(@PathVariable("id") long id, @ModelAttribute("device") Device device) {
        devicesDAO.update(id, device);
        return "redirect:/smarthome/devices/{id}";
    }

    @DeleteMapping("/devices/{id}")
    public String deleteDevice(@PathVariable("id") long id) {
        devicesDAO.delete(id);
        return "redirect:/smarthome/devices";
    }
}