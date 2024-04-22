package com.sportline.service.impl;

import com.sportline.exc.CustomNotFoundException;
import com.sportline.model.entity.Brand;
import com.sportline.repository.BrandRepository;
import com.sportline.service.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class BrandServiceImpl implements BrandService {


    private final BrandRepository brandRepository;
@Transactional
    @Override
    public void create(Brand brand) {
        brandRepository.save(brand);
    }

    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Override
    public Page<Brand> findAllByPage(int pageNum, int pageSize) {
        return brandRepository.findAll(PageRequest.of(pageNum, pageSize));
    }

    @Override
    public Brand getById(Long id) {
        return brandRepository
                .findByIdWithItems(id)
                .orElseThrow(() -> new CustomNotFoundException("Бренд", id));
    }

    @Transactional
    @Override
    public void updateById(Brand brand, long id) {
        brand.setId(id);
        brandRepository.save(brand);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        brandRepository.deleteById(id);
    }
}
