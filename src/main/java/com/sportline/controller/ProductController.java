package com.sportline.controller;


import com.sportline.model.entity.Product;
import com.sportline.model.entity.Status;
import com.sportline.security.UserDetailsWrapper;
import com.sportline.service.BrandService;
import com.sportline.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@AllArgsConstructor
@RequestMapping("/sportline/products")
public class ProductController {

    private static final String REDIRECT_LIST = "redirect:/sportline/products";
    private static final String REDIRECT_ITEM = "redirect:/sportline/products/{id}";

    private ProductService productService;
    private BrandService brandService;

    @PostMapping("")
    public String postProduct(@ModelAttribute("product") Product product) {
        productService.create(product);
        return REDIRECT_LIST;
    }

    @PutMapping("/{id}")
    public String putProduct(@PathVariable("id") long id,
                             @ModelAttribute("product") Product product) {
        productService.updateById(product, id);
        return REDIRECT_ITEM;
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") long id) {
        productService.deleteById(id);
        return REDIRECT_LIST;
    }

    @GetMapping("")
    public String getProducts(Authentication authentication,
                              Model model,
                              @ModelAttribute("product") Product product,
                              @RequestParam(name = "pageNum", required = false, defaultValue = "0") int pageNum,
                              @RequestParam(name = "pageSize", required = false, defaultValue = "4") int pageSize,
                              @RequestParam(name = "searchName", required = false, defaultValue = "") String searchName,
                              @RequestParam(name = "fieldName", required = false, defaultValue = "name") String fieldName,
                              @RequestParam(name = "asc", required = false, defaultValue = "false") Boolean ascending
    ) {
        if (authentication != null) {
            UserDetailsWrapper userDetailsWrapper = (UserDetailsWrapper) authentication.getPrincipal();
            model.addAttribute("userDetailsWrapper", userDetailsWrapper);
        }

        Page<Product> productPage = productService.findAllByPageAndName(pageNum, pageSize, searchName, fieldName, ascending);
        model.addAttribute("statusesArray", Status.values());


        Map<String, String> fieldNamesMap = new HashMap<>();
        fieldNamesMap.put("name", "Названию");
        fieldNamesMap.put("price", "Цене");
        model.addAttribute("asc", ascending);
        model.addAttribute("fieldName", fieldName);
        model.addAttribute("fieldNamesMap", fieldNamesMap);
        model.addAttribute("searchName", searchName);

        model.addAttribute("productsPage", productPage);
        model.addAttribute("productsList", productPage.getContent());
        model.addAttribute("brandsList", brandService.findAll());
        return "sportline/product/list";
    }

    @GetMapping("/{id}")
    public String getProduct(Authentication authentication,
                             Model model,
                             @PathVariable("id") long id) {
        if (authentication != null) {
            UserDetailsWrapper userDetailsWrapper = (UserDetailsWrapper) authentication.getPrincipal();
            model.addAttribute("userDetailsWrapper", userDetailsWrapper);
        }
        model.addAttribute("productItem", productService.getById(id));
        model.addAttribute("brandsList", brandService.findAll());
        model.addAttribute("statusesArray", Status.values());

        return "sportline/product/item";
    }
}