package com.wath.thymeleafdemo.controller.admin;

import com.wath.thymeleafdemo.model.Article;
import com.wath.thymeleafdemo.service.admin.imp.ArticleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class DashboardController {

    private final ArticleServiceImp articleServiceImp;

    @Autowired
    public DashboardController(ArticleServiceImp articleServiceImp) {
        this.articleServiceImp = articleServiceImp;
    }

    @GetMapping("")
    public String dashboard(ModelMap map){
        List<Article> articles = articleServiceImp.getAll();
        map.addAttribute("articles", articles);
        return "admin/dashboard";
    }

}
