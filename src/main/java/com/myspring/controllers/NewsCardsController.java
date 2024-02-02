package com.myspring.controllers;

import com.myspring.models.NewsCard;
import com.myspring.repositories.NewsCardsRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/smarthome")
public class NewsCardsController {

    private NewsCardsRepository newsCardsRepository;

    @PatchMapping({"/newscards/{id}", "/newscards/{id}/"})
    public String patchNewscard(@PathVariable("id") long id,
                                @ModelAttribute("newscard") @Valid NewsCard newsCard,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "smart_home/item_newscard";
        }
        newsCardsRepository.updateById(id, newsCard);
        return "redirect:/smarthome/newscards/{id}";
    }

    @DeleteMapping({"/newscards/{id}", "/newscards/{id}/"})
    public String deleteNewscard(@PathVariable("id") long id) {
        newsCardsRepository.deleteById(id);
        return "redirect:/smarthome/newscards";
    }

    @GetMapping({"/newscards", "/newscards/"})
    public String getNewscards(Model model) {
        model.addAttribute("newscards", newsCardsRepository.findAll());
        return "smart_home/list_newscards";
    }

    @GetMapping({"/newscards/{id}", "/newscards/{id}/"})
    public String getNewsCard(@PathVariable("id") long id, Model model) {
        model.addAttribute("newscard", newsCardsRepository.findById(id).orElse(null));
        return "smart_home/item_newscard";
    }
}
