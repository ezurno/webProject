package com.example.firstproject.repository;

import com.example.firstproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> { //<관리할 대상, 아이디의 타입>
    // 특정 게시글의 모든 댓글 조회
    @Query(value =
            "SELECT * " +
            "FROM comment " +
            "WHERE article_id = :articleId",
            nativeQuery = true) // nativeQuery를 true로 해야 해당 DB가 직접동작
    List<Comment> findByArticleId(@Param("articleId")Long articleId); // 위 Query 문의 :articleId와 매개변수 명이 같아야함
    // 매개변수에 @Param을 사용해 articleId를 연결
    // -> Long타입 articleId를 입력했을때 Comment의 묶음을 반환한다는 뜻
    // 특정 닉네임의 모든 댓글 조회

    List<Comment> findByNickname(@Param("nickname")String nickname);

}
