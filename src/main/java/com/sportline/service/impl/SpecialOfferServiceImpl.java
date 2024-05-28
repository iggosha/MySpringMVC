package com.sportline.service.impl;


import com.sportline.exc.custom.CustomNotFoundException;
import com.sportline.model.entity.SpecialOffer;
import com.sportline.repository.SpecialOfferRepository;
import com.sportline.service.SpecialOfferService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class SpecialOfferServiceImpl implements SpecialOfferService {

    private final SpecialOfferRepository specialOfferRepository;

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public void create(SpecialOffer specialOffer) {
        specialOfferRepository.save(specialOffer);
    }


    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public void updateById(SpecialOffer specialOffer, long id) {
        SpecialOffer specialOfferBefore = getById(id);
        specialOffer.setId(id);
        if (specialOffer.getStartDate() == null) {
            specialOffer.setStartDate(specialOfferBefore.getStartDate());
        }
        if (specialOffer.getEndDate() == null) {
            specialOffer.setEndDate(specialOfferBefore.getEndDate());
        }
        specialOfferRepository.save(specialOffer);
    }

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public void deleteById(Long id) {
        specialOfferRepository.deleteById(id);
    }
    @Override
    public List<SpecialOffer> findAll() {
        return specialOfferRepository.findAll();
    }

    @Override
    public Page<SpecialOffer> findAllByPage(int pageNum, int pageSize) {
        return specialOfferRepository.findAll(PageRequest.of(pageNum, pageSize));
    }

    @Override
    public SpecialOffer getById(Long id) {
        return specialOfferRepository
                .findById(id)
                .orElseThrow(() -> new CustomNotFoundException("Специальное предложение", id));
    }
}