package com.zhcode.personalbloggingapi.service;

import com.zhcode.personalbloggingapi.domain.Article;
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

    public ArticleResponse create(ArticleCreateRequest req){
        Article a = new Article();
        a.setTitle(req.getTitle());
        a.setContent(req.getContent());
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

    public void delete(Long id){
        if (!articleRepository.existsById(id)) {

            throw new RuntimeException("Article not found" + id);
        }
        articleRepository.deleteById(id);
    }

    public ArticleResponse update(Long id, ArticleUpdateRequest req){
        Article a = articleRepository.findById(id).orElseThrow(()-> new RuntimeException("Article not found: " + id));
        a.setTitle(req.getTitle());
        a.setContent(req.getContent());

        Article saved = articleRepository.save(a);
        return toResponse(saved);
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
