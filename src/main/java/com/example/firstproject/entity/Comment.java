package com.example.firstproject.entity;

import com.example.firstproject.dto.CommentDto;
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

    public static Comment createComment(CommentDto dto, Article article) {
        // 예외처리
        if (dto.getId() != null)
            throw  new IllegalArgumentException("댓글 생성 실패 ! 댓글의 아이디가 없어야합니다.");
        if (dto.getArticleId() != article.getId())
            throw  new IllegalArgumentException("댓글 생성 실패 ! 게시글의 id가 잘못되었습니다.");
        // 엔티티 생성 및 반환
        return new Comment(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody()
        );
    }

    public void patch(CommentDto dto) {
        // 예외발생
        if (this.id != dto.getId()) {
            throw new IllegalArgumentException("댓글 수정 실패 ! 잘못된 id가 입력되었습니다.");
        }

        // 객체를 갱신
        if (dto.getNickname() != null) {
            this.nickname = dto.getNickname();
        }

        if (dto.getBody() != null) {
            this.body = dto.getBody();
        }

        // 객체 갱신 *예외가 발생하지 않았을때
    }
}
