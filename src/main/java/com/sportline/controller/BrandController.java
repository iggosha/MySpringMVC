package com.sportline.controller;


import com.sportline.model.entity.Brand;
import com.sportline.service.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/sportline/brands")
public class BrandController {

    private static final String REDIRECT_LIST = "redirect:/sportline/brands";
    private static final String REDIRECT_ITEM = "redirect:/sportline/brands/{id}";

    private BrandService brandService;

    @PostMapping("")
    public String postBrand(@ModelAttribute("brand") Brand brand) {
        brandService.create(brand);
        return REDIRECT_LIST;
    }

    @PutMapping("/{id}")
    public String putBrand(@PathVariable("id") long id,
                           @ModelAttribute("brand") Brand brand) {
        brandService.updateById(brand, id);
        return REDIRECT_ITEM;
    }

    @DeleteMapping("/{id}")
    public String deleteBrand(@PathVariable("id") long id) {
        brandService.deleteById(id);
        return REDIRECT_LIST;
    }

    @GetMapping("")
    public String getBrands(Model model,
                            @ModelAttribute("brand") Brand brand,
                            @RequestParam(name = "pageNum", required = false, defaultValue = "0") int pageNum,
                            @RequestParam(name = "pageSize", required = false, defaultValue = "4") int pageSize) {
        model.addAttribute("brandsPage", brandService.findAllByPage(pageNum, pageSize));
        model.addAttribute("brandsList", brandService.findAllByPage(pageNum, pageSize).getContent());
        return "sportline/brand/list";
    }

    @GetMapping("/{id}")
    public String getBrand(Model model,
                           @PathVariable("id") long id) {
        model.addAttribute("brandItem", brandService.getById(id));
        return "sportline/brand/item";
    }
}