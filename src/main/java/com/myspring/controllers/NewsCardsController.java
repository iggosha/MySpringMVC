package com.myspring.controllers;

import com.myspring.models.NewsCard;
import com.myspring.services.AuthorsService;
import com.myspring.services.NewsCardsService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/smarthome/newscards")
public class NewsCardsController {

    private NewsCardsService newsCardsService;
    private AuthorsService authorsService;

    @PatchMapping({"/{id}", "/{id}/"})
    public String patchNewsCard(@PathVariable("id") long id,
                                @ModelAttribute("newscard") @Valid NewsCard newsCard,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "smart_home/item_newscard";
        }
        newsCardsService.updateById(id, newsCard);
        return "redirect:/smarthome/newscards/{id}";
    }

    @PatchMapping({"/{id}/rating", "/{id}/rating/"})
    public String patchNewsCardRating(@PathVariable("id") long id, @RequestParam("ratingDifference") int ratingDifference) {
        newsCardsService.updateRatingById(id, ratingDifference);
        return "redirect:/smarthome/newscards/{id}";
    }

    @DeleteMapping({"/{id}", "/{id}/"})
    public String deleteNewsCard(@PathVariable("id") long id) {
        newsCardsService.deleteById(id);
        return "redirect:/smarthome/newscards";
    }

    @GetMapping({"", "/"})
    public String getNewsCards(Model model,
                               @RequestParam(name = "pageNum", required = false, defaultValue = "0") int pageNum,
                               @RequestParam(name = "pageSize", required = false, defaultValue = "4") int pageSize
    ) {
        model.addAttribute("newscards", newsCardsService.findAll(pageNum, pageSize));
        return "smart_home/list_newscards";
    }

    @GetMapping({"/{id}", "/{id}/"})
    public String getNewsCard(@PathVariable("id") long id, Model model) {
        model.addAttribute("newscard", newsCardsService.getById(id));
        model.addAttribute("authors", authorsService.findAll());
        return "smart_home/item_newscard";
    }
}
