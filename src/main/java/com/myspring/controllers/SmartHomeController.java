package com.myspring.controllers;

import com.myspring.models.Device;
import com.myspring.repositories.DevicesRepository;
import com.myspring.utils.DeviceValidator;
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
    private DeviceValidator deviceValidator;

    @PatchMapping({"/devices/{id}","/devices/{id}/"})
    public String patchDevice(@PathVariable("id") long id,
                              @ModelAttribute("device") @Valid Device device,
                              BindingResult bindingResult) {
        deviceValidator.validate(device, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/smart_home/device";
        }
        devicesRepository.updateById(id, device);
        return "redirect:/smarthome/devices/{id}";
    }

    @GetMapping({"", "/"})
    public String getStart(Model model) {
        model.addAttribute("devices", devicesRepository.findBunchOfLastLimited(4));
        return "smart_home/start";
    }

    @GetMapping({"/devices","/devices/"})
    public String getDevices(Model model) {
        model.addAttribute("devices", devicesRepository.findAll());
        return "smart_home/devices";
    }

    @GetMapping({"/devices/{id}", "/devices/{id}/"})
    public String getDevice(@PathVariable("id") long id, Model model) {
        model.addAttribute("device", devicesRepository.findById(id).orElse(null));
        return "smart_home/device";
    }

    @DeleteMapping({"/devices/{id}", "/devices/{id}/"})
    public String deleteDevice(@PathVariable("id") long id) {
        devicesRepository.deleteById(id);
        return "redirect:/smarthome/devices";
    }
}
