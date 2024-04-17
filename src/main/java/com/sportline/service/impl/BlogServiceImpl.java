package com.sportline.service.impl;

import com.sportline.exc.CustomNotFoundException;
import com.sportline.model.entity.BlogPost;
import com.sportline.repository.BlogRepository;
import com.sportline.service.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;

    @Override
    public void create(BlogPost blogPost) {
        blogPost.setPublicationDate(LocalDateTime.now());
        blogRepository.save(blogPost);
    }

    @Override
    public List<BlogPost> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Page<BlogPost> findAllByPage(int pageNum, int pageSize) {
        return blogRepository.findAll(PageRequest.of(pageNum, pageSize));
    }

    @Override
    public BlogPost getById(Long id) {
        return blogRepository
                .findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Запись в блоге", id));
    }

    @Override
    public void updateById(BlogPost blogPost, long id) {
        blogPost.setId(id);
        blogPost.setContent(blogPost.getContent() + "\nИзменено: " + LocalDateTime.now());
        blogRepository.save(blogPost);
    }

    @Override
    public void deleteById(Long id) {
        blogRepository.deleteById(id);
    }
}
