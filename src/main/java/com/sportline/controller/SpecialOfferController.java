package com.sportline.controller;

import com.sportline.model.entity.SpecialOffer;
import com.sportline.service.SpecialOfferService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/sportline/offers")
public class SpecialOfferController {

    private static final String REDIRECT_LIST = "redirect:/sportline/offers";
    private static final String REDIRECT_ITEM = "redirect:/sportline/offers/{id}";

    private SpecialOfferService specialOfferService;

    @PostMapping("")
    public String postSpecialOffer(@ModelAttribute("offer") SpecialOffer specialOffer) {
        specialOfferService.create(specialOffer);
        return REDIRECT_LIST;
    }

    @PutMapping("/{id}")
    public String putSpecialOffer(@PathVariable("id") long id,
                                  @ModelAttribute("offer") SpecialOffer specialOffer) {
        specialOfferService.updateById(specialOffer, id);
        return REDIRECT_ITEM;
    }

    @DeleteMapping("/{id}")
    public String deleteSpecialOffer(@PathVariable("id") long id) {
        specialOfferService.deleteById(id);
        return REDIRECT_LIST;
    }

    @GetMapping("")
    public String getSpecialOffer(Model model,
                                  @ModelAttribute("offer") SpecialOffer specialOffer,
                                  @RequestParam(name = "pageNum", required = false, defaultValue = "0") int pageNum,
                                  @RequestParam(name = "pageSize", required = false, defaultValue = "4") int pageSize) {
        model.addAttribute("offersPage", specialOfferService.findAllByPage(pageNum, pageSize));
        model.addAttribute("offersList", specialOfferService.findAllByPage(pageNum, pageSize).getContent());
        return "sportline/offer/list";
    }

    @GetMapping("/{id}")
    public String getSpecialOffer(Model model,
                                  @PathVariable("id") long id) {
        model.addAttribute("offerItem", specialOfferService.getById(id));
        return "sportline/offer/item";
    }
}