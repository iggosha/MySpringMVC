package com.myspring.repositories;

import com.myspring.config.SpringConfig;
import com.myspring.models.Device;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DevicesDAO {

    private final Connection connection;

    public DevicesDAO(SpringConfig springConfig) {
        try {
            connection = DriverManager.getConnection(
                    springConfig.getDatabaseAuthData().get(0),
                    springConfig.getDatabaseAuthData().get(1),
                    springConfig.getDatabaseAuthData().get(2)
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(Device device) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO devices(name, details, price) VALUES(?,?,?)")) {
            preparedStatement.setString(1, device.getName());
            preparedStatement.setString(2, device.getDetails());
            preparedStatement.setDouble(3, device.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Device> findAll() {
        List<Device> devices = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM devices");
            while (resultSet.next()) {
                Device device = new Device();
                device.setId(resultSet.getInt("id"));
                device.setName(resultSet.getString("name"));
                device.setDetails(resultSet.getString("details"));
                device.setPrice(resultSet.getDouble("price"));
                devices.add(device);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return devices;
    }

    public Device findById(long id) {
        Device device = null;
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM devices WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            device = new Device();
            device.setId(resultSet.getInt("id"));
            device.setName(resultSet.getString("name"));
            device.setDetails(resultSet.getString("details"));
            device.setPrice(resultSet.getDouble("price"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return device;
    }

    public void update(long id, Device device) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("UPDATE devices SET name = ?, details = ?, price = ? WHERE id = ?")) {
            preparedStatement.setString(1, device.getName());
            preparedStatement.setString(2, device.getDetails());
            preparedStatement.setDouble(3, device.getPrice());
            preparedStatement.setLong(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(long id) {
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("DELETE FROM devices WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }    }

}
