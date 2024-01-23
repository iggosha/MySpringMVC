package com.myspring.repositories;

import com.myspring.models.Device;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DevicesDAO {

    private static long idCounter = 1L;
    private final List<Device> devices;

    public DevicesDAO() {
        devices = new ArrayList<>();
        devices.add(new Device(idCounter++, "Умная лампа", null, 123));
        devices.add(new Device(idCounter++, "Умная розетка", null, 123));
        devices.add(new Device(idCounter++, "Датчик температуры и влажности", null, 123));
    }

    public List<Device> findAll() {
        return devices;
    }

    public Device findById(long id) {
        return devices
                .stream()
                .filter(device -> device.getId() == id)
                .findFirst()
                .orElse(null);
    }

}
