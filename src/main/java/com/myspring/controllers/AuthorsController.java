package com.myspring.controllers;

import com.myspring.models.Author;
import com.myspring.repositories.AuthorsRepository;
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
public class AuthorsController {

    private NewsCardsRepository newsCardsRepository;
    private AuthorsRepository authorsRepository;

    @PatchMapping({"/authors/{id}", "/authors/{id}/"})
    public String patchAuthor(@PathVariable("id") long id,
                              @ModelAttribute("author") @Valid Author author,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "smart_home/item_author";
        }
        authorsRepository.updateById(id, author);
        return "redirect:/smarthome/authors/{id}";
    }

    @DeleteMapping({"/authors/{id}", "/authors/{id}/"})
    public String deleteAuthor(@PathVariable("id") long id) {
        authorsRepository.deleteById(id);
        return "redirect:/smarthome/authors";
    }

    @GetMapping({"/authors", "/authors/"})
    public String getAuthors(Model model) {
        model.addAttribute("authors", authorsRepository.findAll());
        return "smart_home/list_authors";
    }

    @GetMapping({"/authors/{id}", "/authors/{id}/"})
    public String getAuthor(@PathVariable("id") long id, Model model) {
        model.addAttribute("newscards", newsCardsRepository.findByAuthorId(id));
        model.addAttribute("author", authorsRepository.findById(id).orElse(null));
        return "smart_home/item_author";
    }
}
