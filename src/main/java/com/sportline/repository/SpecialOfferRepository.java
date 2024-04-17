package com.sportline.repository;

import com.sportline.model.entity.SpecialOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SpecialOfferRepository extends JpaRepository<SpecialOffer, Long>, PagingAndSortingRepository<SpecialOffer, Long> {

}
