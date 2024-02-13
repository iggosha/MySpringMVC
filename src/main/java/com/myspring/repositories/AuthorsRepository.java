package com.myspring.repositories;

import com.myspring.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorsRepository extends JpaRepository<Author, Long>, PagingAndSortingRepository<Author, Long> {

//    List<Author> findAllByOrderByFullNameAsc(Pageable pageable);
}