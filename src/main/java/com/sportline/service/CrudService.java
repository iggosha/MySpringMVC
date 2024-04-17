package com.sportline.service;

import org.springframework.data.domain.Page;

import java.util.List;

public interface CrudService<T> {

    void create(T entity);

    List<T> findAll();

    Page<T> findAllByPage(int pageNum, int pageSize);

    T getById(Long id);

    void updateById(T entity, long id);

    void deleteById(Long id);

}
