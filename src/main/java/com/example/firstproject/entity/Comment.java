package com.example.firstproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 생성전략으로 DB가 자동으로 하나씩 증가하게함
    private Long id;

    @ManyToOne // 다대 일. -> 하나의 게시물에 여러개의 댓글이 연결
    @JoinColumn(name = "article_id") // "articleid" 컬럼에 Article의 대푯값을 저장
    private Article article; //댓글의 부모게시글

    @Column
    private String nickname;

    @Column
    private String body;

}
