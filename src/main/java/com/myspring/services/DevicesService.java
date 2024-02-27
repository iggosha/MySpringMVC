package com.myspring.services;

import com.myspring.models.Device;
import com.myspring.repositories.DevicesRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class DevicesService {


    private final DevicesRepository devicesRepository;

    public List<Device> findAll() {
        return devicesRepository.findAll();
    }

    public Device findByIdNonOptional(Long id) {
        return devicesRepository.findById(id).orElse(null);
    }

    public List<Device> findMultipleNewest(Integer size) {
        Pageable page = PageRequest.of(0, size);
        return devicesRepository.findAllByOrderByIdDesc(page).toList();
    }

    @Transactional
    public void save(Device device) {
        devicesRepository.save(device);
    }

    @Transactional
    public void updateById(Long id, Device device) {
        device.setId(id);
        devicesRepository.save(device);
    }

    @Transactional
    public void deleteById(Long id) {
        devicesRepository.deleteById(id);
    }
}
