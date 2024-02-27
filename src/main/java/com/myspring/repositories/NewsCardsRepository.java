package com.myspring.repositories;

import com.myspring.models.NewsCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsCardsRepository extends JpaRepository<NewsCard, Long>, PagingAndSortingRepository<NewsCard, Long> {

    List<NewsCard> findAllByAuthor_Id(Long authorId);

    Page<NewsCard> findAllByOrderByIdDesc(Pageable pageable);
}