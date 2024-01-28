package com.myspring.repositories;

import com.myspring.models.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DevicesDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DevicesDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Device device) {
        jdbcTemplate.update(
                "INSERT INTO devices(name, details, price) VALUES(?,?,?)",
                device.getName(), device.getDetails(), device.getPrice()
        );
    }

    public List<Device> findAll() {
        return jdbcTemplate
                .query(
                        "SELECT * FROM devices",
                        new DeviceMapper()
                );
    }

    public Device findById(long id) {
        return jdbcTemplate
                .query(
                        "SELECT * FROM devices WHERE id = ?", new DeviceMapper(),
                        id
                )
                .stream()
                .findAny()
                .orElse(null);
    }

    public void update(long id, Device device) {
        jdbcTemplate.update(
                "UPDATE devices SET name = ?, details = ?, price = ? WHERE id = ?",
                device.getName(), device.getDetails(), device.getPrice(), id
        );
    }

    public void delete(long id) {
        jdbcTemplate
                .update(
                        "DELETE FROM devices WHERE id = ?",
                        id
                );
    }
}
