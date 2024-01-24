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
        devices.add(new Device(idCounter++, "Умная лампа", "", 111.0));
        devices.add(new Device(idCounter++, "Умная розетка", "", 222.0));
        devices.add(new Device(idCounter++, "Датчик температуры и влажности", "", 333.0));
    }

    public void save(Device device) {
        device.setId(idCounter++);
        devices.add(device);
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

    public void update(long id, Device device) {
        Device deviceToUpdate = findById(id);
        deviceToUpdate.setName(device.getName());
        deviceToUpdate.setDetails(device.getDetails());
        deviceToUpdate.setPrice(device.getPrice());
    }

    public void delete(long id) {
        devices.removeIf(device -> device.getId() == id);
    }

}
