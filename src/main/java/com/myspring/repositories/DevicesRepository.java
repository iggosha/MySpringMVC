package com.myspring.repositories;

import com.myspring.models.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevicesRepository extends PagingAndSortingRepository<Device, Long>, JpaRepository<Device, Long> {

    Page<Device> findAllByOrderByIdDesc(Pageable pageable);
}