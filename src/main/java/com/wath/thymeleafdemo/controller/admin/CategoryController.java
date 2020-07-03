package com.wath.thymeleafdemo.controller.admin;

import com.wath.thymeleafdemo.model.Category;
import com.wath.thymeleafdemo.service.admin.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {

    private CategoryServiceImp categoryServiceImp;
    private String CATEGORY_VIEW = "admin/category";

    @Autowired
    public CategoryController(CategoryServiceImp categoryServiceImp) {
        this.categoryServiceImp = categoryServiceImp;
    }

    @GetMapping
    public String categoryView(Category category, ModelMap map) {
        map.addAttribute("category", category);
        map.addAttribute("categories",categoryServiceImp.select());
        return CATEGORY_VIEW;
    }

    @PostMapping
    public String saveCategoryAction(@Valid @ModelAttribute Category category, BindingResult result,
                                     RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return CATEGORY_VIEW;
        }

        if (categoryServiceImp.save(category) != null) {
            //System.out.println(category + "insert successfully");
            redirect.addFlashAttribute("isSaved","true");
            redirect.addFlashAttribute("message","Record saved..!");

        }
        return "redirect:/admin/categories";
    }
}
