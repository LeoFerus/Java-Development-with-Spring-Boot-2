package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@ToString  // 데이터를 잘 받았는지 확인 할 toString()메서드 추가
public class ArticleForm {

    private String title; //제목을 받을 필드
    private String content; //내용을 받을 필드



    // 데이터를 잘 받았는지 확인 할 toString()메서드 추가

    public Article toEntity() {
        return new Article(null,title,content);
    }
}


