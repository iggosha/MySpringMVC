package com.myspring.controllers;

import com.myspring.models.Device;
import com.myspring.services.DevicesService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/smarthome")
public class DevicesController {

    private DevicesService devicesService;

    @PatchMapping({"/devices/{id}", "/devices/{id}/"})
    public String patchDevice(@PathVariable("id") long id,
                              @ModelAttribute("device") @Valid Device device,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "smart_home/item_device";
        }
        devicesService.updateById(id, device);
        return "redirect:/smarthome/devices/{id}";
    }

    @DeleteMapping({"/devices/{id}", "/devices/{id}/"})
    public String deleteDevice(@PathVariable("id") long id) {
        devicesService.deleteById(id);
        return "redirect:/smarthome/devices";
    }


    @GetMapping({"/devices", "/devices/"})
    public String getDevices(Model model) {
        model.addAttribute("devices", devicesService.findAll());
        return "smart_home/list_devices";
    }


    @GetMapping({"/devices/{id}", "/devices/{id}/"})
    public String getDevice(@PathVariable("id") long id, Model model) {
        model.addAttribute("device", devicesService.findById(id));
        return "smart_home/item_device";
    }
}
