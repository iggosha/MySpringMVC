package com.sportline.repositories;

import com.sportline.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorsRepository extends JpaRepository<Author, Long>, PagingAndSortingRepository<Author, Long> {

    @Query("SELECT SUM (n.ratingPoints) FROM NewsCard n WHERE n.author.id = ?1")
    Integer getSummaryRatingById(Long authorId);

    @Query("SELECT a FROM Author a LEFT JOIN NewsCard nc ON a.id = nc.author.id WHERE a.id = ?1")
    Optional<Author> findByIdWithItems(Long id);
}