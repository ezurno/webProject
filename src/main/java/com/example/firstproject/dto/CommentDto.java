package com.example.firstproject.dto;

import com.example.firstproject.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor // 모든생성자 자동 생성
@NoArgsConstructor // 디폴트 생성자 ''
@Getter
@ToString
public class CommentDto {
    private Long id;
    // @JsonProperty("article_id") -> article_id를 자동으로 변환해줌
    private Long articleId;
    private String nickname;
    private String body;

    public static CommentDto createCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getArticle().getId(),
                comment.getNickname(),
                comment.getBody()
        );
    }
}
