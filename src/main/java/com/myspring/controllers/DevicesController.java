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
@RequestMapping("/smarthome/devices")
public class DevicesController {

    private DevicesService devicesService;

    @PatchMapping({"/{id}", "/{id}/"})
    public String patchDevice(@PathVariable("id") long id,
                              @ModelAttribute("device") @Valid Device device,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "smart_home/item_device";
        }
        devicesService.updateById(id, device);
        return "redirect:/smarthome/devices/{id}";
    }

    @DeleteMapping({"/{id}", "/{id}/"})
    public String deleteDevice(@PathVariable("id") long id) {
        devicesService.deleteById(id);
        return "redirect:/smarthome/devices";
    }


    @GetMapping({"", "/"})
    public String getDevices(Model model,
                             @RequestParam(name = "pageNum", required = false, defaultValue = "0") int pageNum,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "4") int pageSize) {
        model.addAttribute("devices", devicesService.findAll(pageNum, pageSize));
        return "smart_home/list_devices";
    }


    @GetMapping({"/{id}", "/{id}/"})
    public String getDevice(@PathVariable("id") long id, Model model) {
        model.addAttribute("device", devicesService.getById(id));
        return "smart_home/item_device";
    }
}
