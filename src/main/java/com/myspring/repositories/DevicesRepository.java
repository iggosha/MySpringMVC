package com.myspring.repositories;

import com.myspring.models.Device;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class DevicesRepository {

    private final SessionFactory sessionFactory;

    @Transactional
    public void save(Device device) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(device);
    }

    @Transactional
    public void updateById(long id, Device device) {
        Session session = sessionFactory.getCurrentSession();
        Device deviceFromDb = session.get(Device.class, id);
        deviceFromDb.setName(device.getName());
        deviceFromDb.setDetails(device.getDetails());
        deviceFromDb.setPrice(device.getPrice());
    }

    @Transactional
    public void deleteById(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Device.class, id));
    }

    @Transactional(readOnly = true)
    public List<Device> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Device> devices = session.createQuery("FROM Device", Device.class).getResultList();
        return devices;
    }

    @Transactional(readOnly = true)
    public List<Device> findBunchOfLastLimited(int limit) {
        Session session = sessionFactory.getCurrentSession();
        Query<Device> query = session.createQuery("FROM Device ORDER BY id DESC", Device.class);
        query.setMaxResults(limit);
        List<Device> devices = query.getResultList();
        return devices;
    }

    @Transactional(readOnly = true)
    public Optional<Device> findById(long id) {
        Session session = sessionFactory.getCurrentSession();
        Optional<Device> device = Optional.of(session.get(Device.class, id));
        return device;
    }
}
