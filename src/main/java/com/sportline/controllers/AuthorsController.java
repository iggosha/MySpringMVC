package com.sportline.controllers;

import com.sportline.models.Author;
import com.sportline.services.AuthorsService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/smarthome/authors")
public class AuthorsController {

    private AuthorsService authorsService;

    @PatchMapping({"/{id}", "/{id}/"})
    public String patchAuthor(@PathVariable("id") long id,
                              @ModelAttribute("author") @Valid Author author,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "smart_home/item_author";
        }
        authorsService.updateById(id, author);
        return "redirect:/smarthome/authors/{id}";
    }

    @DeleteMapping({"/{id}", "/{id}/"})
    public String deleteAuthor(@PathVariable("id") long id) {
        authorsService.deleteById(id);
        return "redirect:/smarthome/authors";
    }

    @GetMapping({"", "/"})
    public String getAuthors(Model model,
                             @RequestParam(name = "pageNum", required = false, defaultValue = "0") int pageNum,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "4") int pageSize) {
        model.addAttribute("authors", authorsService.findAll(pageNum, pageSize));
        return "smart_home/list_authors";
    }

    @GetMapping({"/{id}", "/{id}/"})
    public String getAuthor(@PathVariable("id") long id, Model model) {
        Author author = authorsService.getByIdWithItems(id);
        model.addAttribute("author", author);
        model.addAttribute("rating", authorsService.getSummaryRatingById(author));
        return "smart_home/item_author";
    }
}
