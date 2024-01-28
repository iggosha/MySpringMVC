package com.myspring.repositories;

import com.myspring.models.Device;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class DevicesRepository {

    private final JdbcTemplate jdbcTemplate;

    public void save(Device device) {
        jdbcTemplate.update("INSERT INTO devices(name, details, price) VALUES(?,?,?)",
                device.getName(),
                device.getDetails(),
                device.getPrice());
    }

    public List<Device> findAll() {
        return jdbcTemplate.query("SELECT * FROM devices",
                new BeanPropertyRowMapper<>(Device.class));
    }

    public Device findById(long id) {
        return jdbcTemplate.query("SELECT * FROM devices WHERE id = ?",
                new BeanPropertyRowMapper<>(Device.class),
                id).stream().findAny().orElse(null);
    }

    public void updateById(long id, Device device) {
        jdbcTemplate.update("UPDATE devices SET name = ?, details = ?, price = ? WHERE id = ?",
                device.getName(),
                device.getDetails(),
                device.getPrice(),
                id);
    }

    public void deleteById(long id) {
        jdbcTemplate.update("DELETE FROM devices WHERE id = ?",
                id);
    }
}
