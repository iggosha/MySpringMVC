package com.myspring.repositories;

import com.myspring.models.Device;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class DevicesRepository {

    private final Session session;

    @Transactional
    public void save(Device device) {
        session.beginTransaction();
        session.persist(device);
        session.getTransaction().commit();
    }

    @Transactional
    public void updateById(long id, Device device) {
        session.beginTransaction();
        Device deviceFromDb = session.get(Device.class, id);
        deviceFromDb.setName(device.getName());
        deviceFromDb.setDetails(device.getDetails());
        deviceFromDb.setPrice(device.getPrice());
        session.getTransaction().commit();
    }

    @Transactional
    public void deleteById(long id) {
        session.beginTransaction();
        session.remove(session.get(Device.class, id));
        session.getTransaction().commit();

    }

    @Transactional(readOnly = true)
    public List<Device> findAll() {
        session.beginTransaction();
        List<Device> devices = session.createQuery("FROM Device", Device.class).getResultList();
        session.getTransaction().commit();
        return devices;
    }

    @Transactional(readOnly = true)
    public List<Device> findBunchOfLastLimited(int limit) {
        session.beginTransaction();
        Query<Device> query = session.createQuery("FROM Device ORDER BY id DESC", Device.class);
        query.setMaxResults(limit);
        List<Device> devices = query.getResultList();
        session.getTransaction().commit();
        return devices;
    }

    @Transactional(readOnly = true)
    public Optional<Device> findById(long id) {
        session.beginTransaction();
        Optional<Device> device = Optional.of(session.get(Device.class, id));
        session.getTransaction().commit();
        return device;
    }
}
