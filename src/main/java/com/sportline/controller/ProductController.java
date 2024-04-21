package com.sportline.controller;


import com.sportline.model.entity.Product;
import com.sportline.model.entity.Status;
import com.sportline.service.BrandService;
import com.sportline.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String getProducts(Model model,
                              @ModelAttribute("product") Product product,
                              @RequestParam(name = "pageNum", required = false, defaultValue = "0") int pageNum,
                              @RequestParam(name = "pageSize", required = false, defaultValue = "4") int pageSize) {
        model.addAttribute("productsPage", productService.findAllByPage(pageNum, pageSize));
        model.addAttribute("brandsList", brandService.findAll());
        model.addAttribute("statusesArray", Status.values());
        model.addAttribute("productsList", productService.findAllByPage(pageNum, pageSize).getContent());
        return "sportline/product/list";
    }

    @GetMapping("/{id}")
    public String getProduct(Model model,
                             @PathVariable("id") long id) {
        model.addAttribute("productItem", productService.getById(id));
        model.addAttribute("brandsList", brandService.findAll());
        model.addAttribute("statusesArray", Status.values());

        return "sportline/product/item";
    }
}