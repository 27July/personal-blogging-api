package com.zhcode.personalbloggingapi.repository;

import com.zhcode.personalbloggingapi.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
