package com.zhcode.personalbloggingapi.controller;

import com.zhcode.personalbloggingapi.domain.User;
import com.zhcode.personalbloggingapi.dto.ArticleCreateRequest;
import com.zhcode.personalbloggingapi.dto.ArticleResponse;
import com.zhcode.personalbloggingapi.dto.ArticleUpdateRequest;
import com.zhcode.personalbloggingapi.service.ArticleService;
import com.zhcode.personalbloggingapi.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final AuthService authService;

    public ArticleController(ArticleService articleService, AuthService authService) {
        this.articleService = articleService;
        this.authService = authService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArticleResponse create(@Valid @RequestBody ArticleCreateRequest req, @RequestHeader("Token")String token){
        User currentUser = authService.requireUserFromToken(token);
        return articleService.create(req, currentUser);
    }

    @GetMapping
    public List<ArticleResponse> list(
            @RequestParam(required = false)Boolean mine,
            @RequestHeader(value = "Token", required = false) String token
    ){
        if(Boolean.TRUE.equals(mine)){
            User currentUser = authService.requireUserFromToken(token);
            return articleService.listMine(currentUser);
        }
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
