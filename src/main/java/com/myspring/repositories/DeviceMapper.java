package com.myspring.repositories;

import com.myspring.models.Device;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeviceMapper implements RowMapper<Device> {

    @Override
    public Device mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Device device = new Device();
        device.setId(resultSet.getInt("id"));
        device.setName(resultSet.getString("name"));
        device.setDetails(resultSet.getString("details"));
        device.setPrice(resultSet.getDouble("price"));
        return device;
    }
}
