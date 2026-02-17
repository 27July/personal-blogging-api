package com.zhcode.personalbloggingapi.service;

import com.zhcode.personalbloggingapi.domain.Article;
import com.zhcode.personalbloggingapi.domain.User;
import com.zhcode.personalbloggingapi.dto.ArticleCreateRequest;
import com.zhcode.personalbloggingapi.dto.ArticleResponse;
import com.zhcode.personalbloggingapi.dto.ArticleUpdateRequest;
import com.zhcode.personalbloggingapi.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ArticleResponse create(ArticleCreateRequest req, User author){
        Article a = new Article();
        a.setTitle(req.getTitle());
        a.setContent(req.getContent());
        a.setAuthor(author);
        //We have @PrePersist for the timestamps

        Article saved = articleRepository.save(a);

        return toResponse(saved);
    }

    public List<ArticleResponse> list(){
        return articleRepository.findAll().stream().map(this::toResponse)
                .toList();
    }

    public ArticleResponse getById(Long id){
        Article a = articleRepository.findById(id).orElseThrow(()-> new RuntimeException("Article not found" + id));
        return toResponse(a);
    }

    public void delete(Long id, User currentUser){

        Article a = articleRepository.findById(id).orElseThrow(()-> new RuntimeException("Article not found" + id));
        if (!a.getAuthor().getId().equals(currentUser.getId())){
            throw new RuntimeException("Forbidden: you are not the author");
        }
        articleRepository.deleteById(id);
    }

    public ArticleResponse update(Long id, ArticleUpdateRequest req, User currentUser){
        Article a = articleRepository.findById(id).orElseThrow(()-> new RuntimeException("Article not found: " + id));
        if(!a.getAuthor().getId().equals(currentUser.getId())){
            throw new RuntimeException("Forbidden: you are not the author");
        }
        a.setTitle(req.getTitle());
        a.setContent(req.getContent());

        Article saved = articleRepository.save(a);
        return toResponse(saved);
    }

    public List<ArticleResponse> listMine(User user){
        return articleRepository.findByAuthorId(user.getId()).stream().map(this::toResponse).toList();
    }


    private ArticleResponse toResponse(Article a){
        return new ArticleResponse(a.getId(),
                a.getTitle(),
                a.getContent(),
                a.getCreatedAt(),
                a.getUpdatedAt()
                );
    }
}
