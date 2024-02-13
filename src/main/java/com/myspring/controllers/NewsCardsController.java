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

import java.util.HashMap;
import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("/smarthome")
public class NewsCardsController {

    private NewsCardsService newsCardsService;
    private AuthorsService authorsService;

    @PatchMapping({"/newscards/{id}", "/newscards/{id}/"})
    public String patchNewscard(@PathVariable("id") long id,
                                @ModelAttribute("newscard") @Valid NewsCard newsCard,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "smart_home/item_newscard";
        }
        newsCardsService.updateById(id, newsCard);
        return "redirect:/smarthome/newscards/{id}";
    }

    @DeleteMapping({"/newscards/{id}", "/newscards/{id}/"})
    public String deleteNewscard(@PathVariable("id") long id) {
        newsCardsService.deleteById(id);
        return "redirect:/smarthome/newscards";
    }

    @GetMapping({"/newscards", "/newscards/"})
    public String getNewscards(Model model) {
        model.addAttribute("newscards", newsCardsService.findAll());
        Map<Long, String> authorMap = new HashMap<>();
        authorsService.findAll()
                .forEach(author ->
                        authorMap.put(author.getId(), author.getFullName())
                );
        model.addAttribute("authorMap", authorMap);
        return "smart_home/list_newscards";
    }

    @GetMapping({"/newscards/{id}", "/newscards/{id}/"})
    public String getNewsCard(@PathVariable("id") long id, Model model) {
        model.addAttribute("newscard", newsCardsService.findById(id));
        model.addAttribute("authorObj", authorsService.findByNewsCardId(id));
        model.addAttribute("authors", authorsService.findAll());
        return "smart_home/item_newscard";
    }
}
