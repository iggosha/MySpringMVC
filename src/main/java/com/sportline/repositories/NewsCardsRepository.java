package com.sportline.repositories;

import com.sportline.models.NewsCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsCardsRepository extends JpaRepository<NewsCard, Long>, PagingAndSortingRepository<NewsCard, Long> {

    Page<NewsCard> findAllByOrderByIdDesc(Pageable pageable);
}