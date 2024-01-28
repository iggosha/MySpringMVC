package com.myspring.controllers;

import com.myspring.models.Device;
import com.myspring.repositories.DevicesRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/smarthome")
public class SmartHomeController {

    private DevicesRepository devicesRepository;

    @GetMapping()
    public String getStart(@ModelAttribute("device") Device device) {
        return "smart_home/start";
    }

    @PostMapping()
    public String postDevice(@ModelAttribute("device") @Valid Device device,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/smart_home/start";
        }
        devicesRepository.save(device);
        return "redirect:/smarthome";
    }

    @GetMapping("/devices")
    public String getDevices(Model model) {
        model.addAttribute("devices", devicesRepository.findAll());
        return "smart_home/devices";
    }

    @GetMapping("/devices/{id}")
    public String getDevice(@PathVariable("id") long id, Model model) {
        model.addAttribute("device", devicesRepository.findById(id));
        return "smart_home/device";
    }

    @PatchMapping("/devices/{id}")
    public String patchDevice(@PathVariable("id") long id,
                              @ModelAttribute("device") @Valid Device device,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/smart_home/device";
        }
        devicesRepository.updateById(id, device);
        return "redirect:/smarthome/devices/{id}";
    }

    @DeleteMapping("/devices/{id}")
    public String deleteDevice(@PathVariable("id") long id) {
        devicesRepository.deleteById(id);
        return "redirect:/smarthome/devices";
    }
}
