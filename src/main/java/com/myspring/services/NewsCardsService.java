package com.myspring.services;


import com.myspring.models.NewsCard;
import com.myspring.repositories.NewsCardsRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class NewsCardsService {

    private final NewsCardsRepository newsCardsRepository;

    public List<NewsCard> findAll() {
        return newsCardsRepository.findAll();
    }

    public NewsCard findByIdNonOptional(Long id) {
        return newsCardsRepository.findById(id).orElse(null);
    }

    public List<NewsCard> findByAuthorId(Long authorId) {
        return newsCardsRepository.findAllByAuthor_Id(authorId);
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
        if (newsCard.getRatingPoints() == null) {
            newsCard.setRatingPoints(0);
        }
        newsCardsRepository.save(newsCard);
    }

    @Transactional
    public void updateRatingById(long id, int ratingDifference) {
        NewsCard newsCard = findByIdNonOptional(id);
        int ratingPoints = newsCard.getRatingPoints() + ratingDifference;
        newsCard.setRatingPoints(ratingPoints);
        newsCardsRepository.save(newsCard);
    }

    @Transactional
    public void deleteById(Long id) {
        newsCardsRepository.deleteById(id);
    }

    @Transactional
    public void updateById(Long id, NewsCard newsCard) {
        updateNewsCardWithId(id, newsCard);
    }

    private void updateNewsCardWithId(Long id, NewsCard newsCard) {
        newsCard.setId(id);
        newsCardsRepository.save(newsCard);
    }
}
