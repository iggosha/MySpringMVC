package com.myspring.services;


import com.myspring.models.Author;
import com.myspring.models.NewsCard;
import com.myspring.repositories.AuthorsRepository;
import com.myspring.repositories.NewsCardsRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;


@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class NewsCardsService {

    private final NewsCardsRepository newsCardsRepository;
    private final AuthorsRepository authorsRepository;

    public List<NewsCard> findAll() {
        return newsCardsRepository.findAll();
    }

    public NewsCard findById(Long id) {
        return newsCardsRepository.findById(id).orElse(null);
    }

    public List<NewsCard> findByAuthorId(Long authorId) {
        Author author = authorsRepository
                .findById(authorId)
                .orElseThrow(() -> new NoSuchElementException("No author with given id"));
        return author.getNewsCards();
    }

    public List<NewsCard> findMultipleNewest(Integer size) {
        Pageable page = PageRequest.of(0, size);
        return newsCardsRepository.findAllByOrderByIdDesc(page).toList();
    }


    @Transactional
    public void save(NewsCard newsCard) {
        if (newsCard.getCreationDate() == null) {
            newsCard.setCreationDate(LocalDateTime.now());
        }
        newsCardsRepository.save(newsCard);
    }

    @Transactional
    public void updateById(Long id, NewsCard newsCard) {
        newsCard.setId(id);
        newsCardsRepository.save(newsCard);
    }

    @Transactional
    public void deleteById(Long id) {
        newsCardsRepository.deleteById(id);
    }

//    @Transactional
//    public void delete(NewsCard newsCard) {
//        newsCardsRepository.delete(newsCard);
//    }
}
