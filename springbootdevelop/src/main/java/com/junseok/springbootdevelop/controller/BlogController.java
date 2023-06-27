package com.junseok.springbootdevelop.controller;

import com.junseok.springbootdevelop.domain.Article;
import com.junseok.springbootdevelop.dto.ArticleRequestDto;
import com.junseok.springbootdevelop.dto.ArticleResponseDto;
import com.junseok.springbootdevelop.dto.ArticleUpdateDto;
import com.junseok.springbootdevelop.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController // HTTP ResBody에 객체 데이터를 JSON으로 반환
public class BlogController {

    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> saveArticle(@RequestBody ArticleRequestDto requestDto, Principal principal) {
        Article savedArticle = blogService.save(requestDto, principal.getName());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponseDto>> findAllArticles() {
        List<ArticleResponseDto> responseDto = blogService.findAll()
                .stream()
                .map(ArticleResponseDto::new)
                .toList();

        return ResponseEntity.ok()
                .body(responseDto);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponseDto> findArticle(@PathVariable long id) {
        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponseDto(article));
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id) {
        blogService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id, @RequestBody ArticleUpdateDto updateDto) {
        Article updatedArticle = blogService.update(id, updateDto);

        return ResponseEntity.ok()
                .body(updatedArticle);
    }
}
