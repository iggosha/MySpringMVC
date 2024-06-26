package com.sportline.controller;

import com.sportline.model.entity.BlogPost;
import com.sportline.security.UserDetailsWrapper;
import com.sportline.service.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/sportline/blog")
public class BlogController {

    private static final String REDIRECT_LIST = "redirect:/sportline/blog";
    private static final String REDIRECT_ITEM = "redirect:/sportline/blog/{id}";

    private BlogService blogService;

    @PostMapping("")
    public String postBlogPost(@ModelAttribute("blogPost") BlogPost blogPost) {
        blogService.create(blogPost);
        return REDIRECT_LIST;
    }

    @PutMapping("/{id}")
    public String putBlogPost(@PathVariable("id") long id,
                              @ModelAttribute("blogPost") BlogPost blogPost) {
        blogService.updateById(blogPost, id);
        return REDIRECT_ITEM;
    }

    @DeleteMapping("/{id}")
    public String deleteBlogPost(@PathVariable("id") long id) {
        blogService.deleteById(id);
        return REDIRECT_LIST;
    }

    @GetMapping("")
    public String getBlogPosts(Authentication authentication,
                               Model model,
                               @ModelAttribute("blogPost") BlogPost blogPost,
                               @RequestParam(name = "pageNum", required = false, defaultValue = "0") int pageNum,
                               @RequestParam(name = "pageSize", required = false, defaultValue = "4") int pageSize) {
        if (authentication != null) {
            UserDetailsWrapper userDetailsWrapper = (UserDetailsWrapper) authentication.getPrincipal();
            model.addAttribute("userDetailsWrapper", userDetailsWrapper);
        }
        model.addAttribute("blogPostsPage", blogService.findAllByPage(pageNum, pageSize));
        model.addAttribute("blogPostsList", blogService.findAllByPage(pageNum, pageSize).getContent());
        return "sportline/blog/list";
    }

    @GetMapping("/{id}")
    public String getBlogPost(Authentication authentication,
                              Model model,
                              @PathVariable("id") long id) {
        if (authentication != null) {
            UserDetailsWrapper userDetailsWrapper = (UserDetailsWrapper) authentication.getPrincipal();
            model.addAttribute("userDetailsWrapper", userDetailsWrapper);
        }
        model.addAttribute("blogPostItem", blogService.getById(id));
        return "sportline/blog/item";
    }
}
