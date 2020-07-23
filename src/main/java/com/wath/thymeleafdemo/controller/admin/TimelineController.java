package com.wath.thymeleafdemo.controller.admin;

import com.wath.thymeleafdemo.model.Article;
import com.wath.thymeleafdemo.service.admin.imp.ArticleServiceImp;
import com.wath.thymeleafdemo.service.admin.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class TimelineController {
    private ArticleServiceImp articleServiceImp;

    @Autowired
    public TimelineController(ArticleServiceImp articleServiceImp) {
        this.articleServiceImp = articleServiceImp;
    }

    @GetMapping
    public String timelineView(ModelMap map, @ModelAttribute Article article){
        List<Article> articles = articleServiceImp.getAll();
        map.addAttribute("articles",articles);
        return "index";
    }
}
