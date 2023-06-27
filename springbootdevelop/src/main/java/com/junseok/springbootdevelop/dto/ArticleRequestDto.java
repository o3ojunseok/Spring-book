package com.junseok.springbootdevelop.dto;

import com.junseok.springbootdevelop.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자 추가
@Getter
public class ArticleRequestDto {

    private String title;
    private String content;

    public Article toEntity(String author) { // 빌더 패턴을 사용해 DTO를 엔티티로 만들어주는 메서드
        return Article.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
