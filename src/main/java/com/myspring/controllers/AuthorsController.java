package com.myspring.controllers;

import com.myspring.models.Author;
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
@RequestMapping("/smarthome")
public class AuthorsController {

    private NewsCardsService newsCardsService;
    private AuthorsService authorsService;

    @PatchMapping({"/authors/{id}", "/authors/{id}/"})
    public String patchAuthor(@PathVariable("id") long id,
                              @ModelAttribute("author") @Valid Author author,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "smart_home/item_author";
        }
        authorsService.updateById(id, author);
        return "redirect:/smarthome/authors/{id}";
    }

    @DeleteMapping({"/authors/{id}", "/authors/{id}/"})
    public String deleteAuthor(@PathVariable("id") long id) {
        authorsService.deleteById(id);
        return "redirect:/smarthome/authors";
    }

    @GetMapping({"/authors", "/authors/"})
    public String getAuthors(Model model) {
        model.addAttribute("authors", authorsService.findAll());
        return "smart_home/list_authors";
    }

    @GetMapping({"/authors/{id}", "/authors/{id}/"})
    public String getAuthor(@PathVariable("id") long id, Model model) {
        Author author = authorsService.findByIdNonOptional(id);
        model.addAttribute("newscards", newsCardsService.findByAuthorId(id));
        model.addAttribute("author", author);
        model.addAttribute("rating", authorsService.getSummaryRatingById(author));
        return "smart_home/item_author";
    }
}
