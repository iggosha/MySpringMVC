package com.sportline.service;

import com.sportline.model.entity.Product;
import org.springframework.data.domain.Page;

public interface ProductService extends CrudService<Product> {

    Page<Product> findAllByPageAndName(int pageNum, int pageSize, String searchName, String fieldName, boolean ascending);
}
