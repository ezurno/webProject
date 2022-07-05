package com.example.firstproject.Service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static jdk.nashorn.internal.objects.Global.println;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 해당클래스는 스프링 부트와 연동되어 테스팅 됨
class ArticleServiceTest { //ArticleService를 테스트하기 위한 테스트클래스


    @Autowired ArticleService articleService;
    @Autowired
    private ArticleRepository articleRepository; // ArticleRepository를 사용하기 위해 articleRepository 자동연결 어노테이션
    //ArticleService의 articleService를 자동연결

    @Test // Test코드라 선언 어노테이션
    void index() {
        // 예상
        Article a = new Article(1L, "가가가가","11111");
        Article b = new Article(2L, "나나나나","22222");
        //Article c = new Article(1L, "다다다다","33333");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b));

        // 실체
        List<Article> articles = articleService.index();

        // 비교
        assertEquals(expected.toString(), articles.toString());
        //assertEquals를 이용해 expected 와 articles의 값이 같은지 판별
        //판별시 참이면 단독코드 컴파일에서 오류가 나지않음
    }

    ////////////
    //  TEST  //
    ////////////
    @Test
    void showSuccess() {
        //예상
        Long id = 1L;
        Article expected = new Article(id, "가가가가", "11111");

        //실체
        Article article = articleService.show(id);

        //비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void showFailed() {
        //예상
        Long id = -1L; //-1번 값 -> 없는 번호!
        Article expected = null;

        //실체
        Article article = articleService.show(id);

        //비교
        assertEquals(expected/*.toString()*/, article/*.toString()*/);
        //주석은 expected가 null 값이므로 toString 호출이 안되므로
    }


    @Test
    @Transactional // 데이터초기화를 위한 트랜잭션
    void createSuccess() {
        //예상
        String title = "라라라";
        String content = "4444";


        ArticleForm dto = new ArticleForm(null,title,content);
        Article expected = new Article(3L, title, content);
        //실체
        Article article = articleService.create(dto);

        //비교
        assertEquals(expected.toString(),article.toString());

    }

    @Test
    @Transactional //데이터 초기화를 위한 트랜잭션
    void createFailed() {
        //예상
        String title = "라라라";
        String content = "4444";


        ArticleForm dto = new ArticleForm(3L,title,content);
        Article expected = null;
        //실체
        Article article = articleService.create(dto);

        //비교
        assertEquals(expected,article);
    }


    @Test
    @Transactional
    void updateSuccess_haveAll() {
        //예상
        Long id = 1L;
        String title = "CHANGED_TITLE";
        String content = "CHANGED_CONTENT";

        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = new Article(id, title, content);
        //실체
        Article article = articleService.update(id , dto);

        //비교
        assertEquals(article.toString(), expected.toString());
    }

    @Test
    @Transactional
    void updateSuccess_haveIdTitle() {
        Long id = 1L;
        String title = "CHANGED_TITLE";
        String content = "";

        ArticleForm dto = new ArticleForm(id , title, content);
        Article except = new Article(id, title, content);

        Article article = articleService.update(id, dto);

        assertEquals(except.toString(), article.toString());
    }

    @Test
    @Transactional
    void updateFailed_haveId() {
        Long id = 1L;
        String title = null;
        String content = null;

        ArticleForm dto = new ArticleForm(id , title, content);
        Article expected = null;

        Article article = articleService.update(id , dto);

        assertEquals(expected, article);

    }

    @Test
    @Transactional
    void updateFailed_NoId() {
        Long id = 4L;
        String title = "CHANGED_TITLE";
        String content = "CHANGED_CONTENT";

        ArticleForm dto = new ArticleForm(id, title, content);
        Article except = null;

        Article article = articleService.update(id, dto);

        assertEquals(except, article);

    }

    @Test
    @Transactional
    void deleteSuccess() {

        Long id = 1L;
        String title = articleRepository.findById(id).orElse(null).getTitle();
        String content = articleRepository.findById(id).orElse(null).getContent();

        ArticleForm dto = new ArticleForm(id, title, content);
        Article except = new Article(id, title, content);

        Article article = articleService.delete(id);

        assertEquals(article.toString(), except.toString());



    }

    @Test
    @Transactional
    void deleteFailed() {
        Long id = 4L;

        Article except = null;

        Article article = articleService.delete(id);

        assertEquals(except, article);

    }
}