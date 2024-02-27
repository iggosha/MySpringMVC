package com.myspring.services;

import com.myspring.models.Author;
import com.myspring.repositories.AuthorsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class AuthorsService {

    private final AuthorsRepository authorsRepository;

    public List<Author> findAll() {
        return authorsRepository.findAll();
    }

    public Author findByIdNonOptional(Long id) {
        return authorsRepository.findById(id).orElse(null);
    }

    public Author findByNewsCardId(Long newsCardId) {
        return authorsRepository
                .findAuthorByNewsCardId(newsCardId)
                .orElseThrow(() -> new NoSuchElementException("No newscard with given id"));
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
