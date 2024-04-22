package com.sportline.repository;

import com.sportline.model.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    @Query("SELECT b FROM Brand b LEFT JOIN Product p ON b.id = p.brand.id WHERE b.id = ?1")
    Optional<Brand> findByIdWithItems(Long id);
}
