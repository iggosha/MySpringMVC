package com.sportline.service.impl;

import com.sportline.exc.custom.CustomNotFoundException;
import com.sportline.model.entity.Product;
import com.sportline.repository.ProductRepository;
import com.sportline.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public void create(Product product) {
        productRepository.save(product);
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public void updateById(Product product, long id) {
        product.setId(id);
        productRepository.save(product);
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> findAllByPage(int pageNum, int pageSize) {
        return productRepository.findAll(PageRequest.of(pageNum, pageSize));
    }

    @Override
    public Page<Product> findAllByPageAndName(int pageNum, int pageSize, String nameToFind, String fieldName, boolean ascending) {
        Pageable pageable;
        if (ascending) {
            pageable = PageRequest.of(pageNum, pageSize, Sort.by(fieldName).ascending());
        } else {
            pageable = PageRequest.of(pageNum, pageSize, Sort.by(fieldName).descending());
        }
        return productRepository.findAllByNameContainingIgnoreCase(nameToFind, pageable);
    }

    @Override
    public Product getById(Long id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Товар", id));
    }
}
