package com.myspring.services;

import com.myspring.models.Author;
import com.myspring.models.NewsCard;
import com.myspring.repositories.AuthorsRepository;
import com.myspring.repositories.NewsCardsRepository;
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
    private final NewsCardsRepository newsCardsRepository;

    public List<Author> findAll() {
        return authorsRepository.findAll();
    }

    public Author findById(Long id) {
        return authorsRepository.findById(id).orElse(null);
    }

    public Author findByNewsCardId(Long newsCardId) {
        NewsCard newsCard = newsCardsRepository
                .findById(newsCardId)
                .orElseThrow(() -> new NoSuchElementException("No newscard with given id"));
        return newsCard.getAuthor();
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

//    @Transactional
//    public void delete(Author author) {
//        authorsRepository.delete(author);
//    }
}
