package com.myspring.services;

import com.myspring.models.Author;
import com.myspring.repositories.AuthorsRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class AuthorsService {

    private final AuthorsRepository authorsRepository;

    public List<Author> findAll() {
        return authorsRepository.findAll();
    }

    public Page<Author> findAll(int pageNum, int pageSize) {
        return authorsRepository.findAll(PageRequest.of(pageNum, pageSize));
    }

    public Author getById(Long id) {
        return authorsRepository.findById(id).orElse(null);
    }

    public Author getByIdWithItems(Long id) {
        return authorsRepository.findByIdWithItems(id).orElse(null);
    }

    public Integer getSummaryRatingById(Author author) {
        if (author.getNewsCards() != null && !author.getNewsCards().isEmpty()) {
            return authorsRepository.getSummaryRatingById(author.getId());
        }
        return 0;
    }

    @Transactional
    public void save(Author author) {
        authorsRepository.save(author);
    }

    @Transactional
    public void updateById(Long id, Author author) {
        author.setId(id);
        authorsRepository.save(author);
    }

    @Transactional
    public void deleteById(Long id) {
        authorsRepository.deleteById(id);
    }
}
