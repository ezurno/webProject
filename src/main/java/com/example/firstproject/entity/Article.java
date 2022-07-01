package com.example.firstproject.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Setter // RequestBody 사용 떄문
@Getter // Get함수 어노테이션 *롬복
@NoArgsConstructor // 디폴트생성자 어노테이션
@AllArgsConstructor // this.함수 대신 어노테이션
@ToString // toString 함수 대신 어노테이션
public class Article {

    @Id // 대푯값을 지정 like a 주민등록번호
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 차츰 올라가는 자동생성 이노테이션 1,2,3,..
    //에서 DB가 id를 자동으로 순서를 맞춰 생성하는 어노테이션
    private Long id;

    @Column // 값을 저장할때 column 값으로 엑셀에 저장
    private String title;

    @Column
    private String content;

    public void patch(Article article) { // null 값이 아니면 article의 값을 가져오며 원래 있던 값 유지
        if (article.title != null) {
            this.title = article.title;
        }

        if (article.content != null) {
            this.content = article.content;
        }
    }
    

    /*
    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }
    */
}
