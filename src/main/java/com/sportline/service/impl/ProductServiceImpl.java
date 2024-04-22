package com.sportline.service.impl;

import com.sportline.exc.CustomNotFoundException;
import com.sportline.model.entity.Product;
import com.sportline.repository.ProductRepository;
import com.sportline.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;

    @Transactional
    @Override
    public void create(Product product) {
        productRepository.save(product);
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
    public Product getById(Long id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Товар", id));
    }

    @Transactional
    @Override
    public void updateById(Product product, long id) {
        product.setId(id);
        productRepository.save(product);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
