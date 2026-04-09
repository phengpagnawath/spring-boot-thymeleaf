package com.wath.thymeleafdemo.controller.admin;

import com.wath.thymeleafdemo.model.Article;
import com.wath.thymeleafdemo.service.admin.imp.ArticleServiceImp;
import com.wath.thymeleafdemo.service.admin.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("admin/articles")
public class ArticleController {

    private final String URL = "/admin/articles";
    private final String PATH = "admin/article";

    @Value("${file.server-path}")
    private String serverPath;

    private  ArticleServiceImp articleServiceImp;
    private CategoryServiceImp categoryServiceImp;

    @Autowired
    public ArticleController(ArticleServiceImp articleServiceImp) {
        this.articleServiceImp = articleServiceImp;
    }

    @Autowired
    public void setCategoryServiceImp(CategoryServiceImp categoryServiceImp) {
        this.categoryServiceImp = categoryServiceImp;
    }

    
    @GetMapping
    public String articleView(ModelMap map, @ModelAttribute Article article){
        map.addAttribute("categories",categoryServiceImp.select());
        map.addAttribute("article",article);
        List<Article> articles = articleServiceImp.getAll();
        map.addAttribute("articles",articles);
        return PATH;
    }

    @PostMapping
    public String saveArticleAction(@ModelAttribute Article article, @RequestParam("tn") MultipartFile tn){
        article.setArticleID(UUID.randomUUID().toString());
        article.setAuthor("Admin");
        article.setDate(new Date(System.currentTimeMillis()));
        article.setThumbnail(storeThumbnail(tn));
        articleServiceImp.save(article);
        return "redirect:"+URL;
    }

    @PostMapping("/{articleID}")
    public String updateArticleAction(@ModelAttribute Article article, @RequestParam("tn") MultipartFile tn){
        if(!tn.isEmpty()){
            article.setThumbnail(storeThumbnail(tn));
        }
        articleServiceImp.update(article);
        return "redirect:"+URL;
    }

    @GetMapping("/{id}/delete")
    public String deleteArticleAction(@PathVariable String id){
        articleServiceImp.delete(id);
        return "redirect:"+URL;
    }

    private String storeThumbnail(MultipartFile thumbnail) {
        if (thumbnail == null || thumbnail.isEmpty()) {
            return null;
        }

        String fileName = Objects.requireNonNull(thumbnail.getOriginalFilename(), "Thumbnail filename is required");
        int extensionIndex = fileName.lastIndexOf('.');
        String extension = extensionIndex >= 0 ? fileName.substring(extensionIndex) : "";
        String storedFileName = UUID.randomUUID() + extension;
        Path uploadDirectory = Paths.get(serverPath).toAbsolutePath().normalize();
        Path destination = uploadDirectory.resolve(storedFileName);

        try {
            Files.createDirectories(uploadDirectory);
            Files.copy(thumbnail.getInputStream(), destination);
            return storedFileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store thumbnail", e);
        }
    }
}
