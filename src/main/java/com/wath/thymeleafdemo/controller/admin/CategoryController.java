package com.wath.thymeleafdemo.controller.admin;

import com.wath.thymeleafdemo.model.Category;
import com.wath.thymeleafdemo.service.admin.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

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
        List<Category> categories=categoryServiceImp.select();
        map.addAttribute("category", category);
        map.addAttribute("categories",categories);
        return CATEGORY_VIEW;
    }

    @PostMapping
    public String saveCategoryAction(@Valid @ModelAttribute Category category, BindingResult result,
                                     RedirectAttributes redirect,ModelMap map) {
        if (result.hasErrors()) {
            return categoryView(category,map);
        }

        if (categoryServiceImp.save(category) != null) {
            //System.out.println(category + "insert successfully");
            redirect.addFlashAttribute("isSaved",true);
            redirect.addFlashAttribute("message","Record saved..!");
        }
        return "redirect:/admin/categories";
    }

    @PostMapping("/{id}")
    public String editCategoryAction(@Valid @ModelAttribute Category category,RedirectAttributes redirect){
        if(categoryServiceImp.edit(category)!=null){
            redirect.addFlashAttribute("isSaved",true);
            redirect.addFlashAttribute("message","Record saved..!");
        }
        return "redirect:/admin/categories";
    }

    @GetMapping("/{id}/delete")
    public String deleteCategoryAction(@PathVariable int id){
        categoryServiceImp.delete(id);
        return "redirect:/admin/categories";
    }
}
