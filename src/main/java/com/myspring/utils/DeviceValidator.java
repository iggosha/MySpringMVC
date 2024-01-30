package com.myspring.utils;

import com.myspring.models.Device;
import com.myspring.repositories.DevicesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@AllArgsConstructor
public class DeviceValidator implements Validator {

    private DevicesRepository devicesRepository;

    @Override
    public boolean supports(Class<?> classArg) {
        return Device.class.equals(classArg);
    }

    @Override
    public void validate(Object objectArg, Errors errors) {
        Device device = (Device) objectArg;
        if (devicesRepository.findByName(device.getName()).isPresent()) {
            errors.rejectValue("name",
                    "",
                    "Названия товаров не должны повторяться");
        }
    }
}
