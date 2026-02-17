package com.zhcode.personalbloggingapi.controller;

import com.zhcode.personalbloggingapi.dto.ArticleCreateRequest;
import com.zhcode.personalbloggingapi.dto.ArticleResponse;
import com.zhcode.personalbloggingapi.dto.ArticleUpdateRequest;
import com.zhcode.personalbloggingapi.service.ArticleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArticleResponse create(@Valid @RequestBody ArticleCreateRequest req){
        return articleService.create(req);
    }

    @GetMapping
    public List<ArticleResponse> list(){
        return articleService.list();
    }

    @GetMapping("/{id}")
    public ArticleResponse getOne(@PathVariable Long id){
        return articleService.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        articleService.delete(id);
    }

    @PutMapping("/{id}")
    public ArticleResponse update(@PathVariable Long id, @Valid @RequestBody ArticleUpdateRequest req){
        return articleService.update(id, req);
    }
}
