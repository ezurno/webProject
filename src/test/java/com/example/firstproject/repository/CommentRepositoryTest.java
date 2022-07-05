package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {

    @Autowired CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")//테스트 결과에 보여줄 이름
    void findByArticleId() {
        { /*Case 1 : 3번 게시글의 모든 댓글 조회 */
            // 입력 데이터 준비
            Long articleId = 3L;

            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 수행결과

            // 예상하기
            Article article = new Article(3L, "당신의 인생영화는?", "댓글 ㄱ");
            Comment a = new Comment(1L, article, "PARK", "괴물");
            Comment b = new Comment(2L, article, "KIM", "혹성탈출");
            Comment c = new Comment(3L, article, "LEE", "쇼생크 탈출");

            List <Comment> expected = Arrays.asList(a,b,c);

            // 검증
            assertEquals(expected.toString(), comments.toString(), "3번 게시글의 모든 댓글을 출력");
        }


        { /*Case 2 : 1번 게시글의 모든 댓글 조회 */
            // 입력 데이터 준비
            Long articleId = 1L;

            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 수행결과

            // 예상하기
            Article article = new Article(1L, "가가가가", "11111");

            List <Comment> expected = Arrays.asList();

            // 검증
            assertEquals(expected.toString(), comments.toString(), "1번 게시글은 댓글이 없습니다");
        }

        { /*Case 3 : 999번 게시글의 모든 댓글 조회 */
            // 입력 데이터 준비
            Long articleId = 999L;

            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 수행결과

            // 예상하기
            Article article = new Article(999L, null, null);

            List <Comment> expected = Arrays.asList();

            // 검증
            assertEquals(expected, comments, "999번 게시글은 없습니다.");
        }

        { /*Case 4 : 9번 게시글의 모든 댓글 조회 */
            // 입력 데이터 준비
            Long articleId = 9L;


            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 수행결과

            // 예상하기
            Article article = new Article(9L, null, null);


            List <Comment> expected = Arrays.asList();

            // 검증
            assertEquals(expected.toString(), comments.toString(), "9번 게시글은 없습니다.");
        }

        { /*Case 3 : 999번 게시글의 모든 댓글 조회 */
            // 입력 데이터 준비
            Long articleId = -1L;

            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 수행결과

            // 예상하기
            Article article = new Article(-1L, null, null);

            List <Comment> expected = Arrays.asList();

            // 검증
            assertEquals(expected, comments, "-1번 게시글은 없습니다.");
        }

    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        /* Case 1. "PARK"의 모든 댓글을 조회 */
        {
            // 입력 Data 준비
            String nickname = "PARK";

            // 실제 수행
            List<Comment>comments = commentRepository.findByNickname(nickname);

            // 예상하기
            Comment a = new Comment(1L, new Article(3L, "당신의 인생영화는?", "댓글 ㄱ"), nickname, "괴물");
            Comment b = new Comment(4L, new Article(4L, "당신의 소울푸드는?", "댓글 ㄱㄱ"), nickname, "치킨");
            Comment c = new Comment(7L, new Article(5L, "당신의 취미는?", "댓글 ㄱㄱㄱ"), nickname, "조깅");
            List<Comment> expected = Arrays.asList(a, b, c);

            // 비교
            assertEquals(expected.toString(), comments.toString(), "PARK의 모든 댓글을 출력!");
        }

        /* Case 2. "KIM"의 모든 댓글을 조회 */
        {
            String nickname = "KIM";

            List<Comment>comments = commentRepository.findByNickname(nickname);

            Comment a = new Comment(2L, new Article(3L, "당신의 인생영화는?", "댓글 ㄱ"), nickname, "혹성탈출");
            Comment b = new Comment(5L, new Article(4L, "당신의 소울푸드는?", "댓글 ㄱㄱ"), nickname, "샤브샤브");
            Comment c = new Comment(8L, new Article(5L, "당신의 취미는?", "댓글 ㄱㄱㄱ"), nickname, "유튜브");
            List<Comment> excepted = Arrays.asList(a,b,c);

            assertEquals(excepted.toString(), comments.toString(), "KIM의 모든 댓글 출력!");

        }

        /* Case 3. null의 모든 댓글을 조회 */
        {
            String nickname = null;
            List<Comment> comments = commentRepository.findByNickname(nickname);

            List<Comment> excepted = Arrays.asList();
            assertEquals(excepted, comments, "미입력");
        }

        /* Case 4. ""의 모든 댓글을 조회 */
        {
            String nickname = "";
            List<Comment> comments = commentRepository.findByNickname(nickname);

            List<Comment> excepted = Arrays.asList();
            assertEquals(excepted.toString(), comments.toString(), "공백");
        }

        /* Case 5. "i"의 모든 댓글을 조회 */
        {
            String nickname = "i";
            List<Comment> comments = commentRepository.findByNickname(nickname);

            List<Comment> excepted = Arrays.asList();
            assertEquals(excepted.toString(), comments.toString(), "i");
        }

    }
}