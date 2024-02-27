package com.myspring.repositories;

import com.myspring.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorsRepository extends JpaRepository<Author, Long>, PagingAndSortingRepository<Author, Long> {

    @Query("SELECT a FROM Author a WHERE a.id = (SELECT nc.author.id FROM NewsCard nc WHERE nc.id = ?1)")
    Optional<Author> findAuthorByNewsCardId(Long newsCardId);

    @Query("SELECT SUM (n.ratingPoints) FROM NewsCard n WHERE n.author.id = ?1")
    Integer getSummaryRatingById(Long authorId);
}