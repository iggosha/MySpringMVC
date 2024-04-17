package com.sportline.service;

import com.sportline.model.entity.BlogPost;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BlogService {

    void create(BlogPost blogPost);

    List<BlogPost> findAll();

    Page<BlogPost> findAllByPage(int pageNum, int pageSize);

    BlogPost getById(Long id);

    void updateById(BlogPost blogPost, long id);

    void deleteById(Long id);

}
