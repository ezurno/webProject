package com.example.firstproject.dto;
//form data를 받아올 그릇

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;


@AllArgsConstructor // this. 함수 대신 리팩토리 어노테이션
@ToString // toString 리팩토리 어노테이션
public class ArticleForm {

    private String title;
    private String content;
    private Long id;

    /*
    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }*/

    public Article toEntity() {
        return new Article(id, title, content);
    }
}
