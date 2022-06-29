package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j // log를 사용하기 위해 어노테이션
public class ArticleController {

    @Autowired // 스프링 부트가 미리 생성해놓은 객체를 가져다가 자동연결 원래
    private ArticleRepository articleRepository;// 자바에는 밑에 repositou에 new로 객체를 생성해야되지만 알아서 생성


    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        //System.out.println(form.toString()); -> 실제 사용 x log로 대체
        log.info(form.toString());
        // 1 DTO로 변환 @entity();
        Article article = form.toEntity();
        //System.out.println(article.toString());// 잘변환 되는지 확인 하기 위해 출력
        log.info(article.toString());
        //2. Repository에게 Entity를 DB안에 저장하게 함
        Article saved = articleRepository.save(article);
        //ArticleRepository 에 extend로 확장해 save 기능을 사용하게 함.
        //System.out.println(saved.toString());
        //save가 잘 작동되는지 확인차 출력
        log.info(saved.toString());

        return "";
    }

    @GetMapping("/articles/{id}") /*@PathVariable url Path로부터 입력이된다.  */
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);

            //1. id로 데이터를 가져옴
            Article articleEntity = articleRepository.findById(id).orElse(null);
                // id값을 통해 찾을 때 값이 없으면 null이 담겨짐
            //Optional<Article> articleEntity = articleRepository.findById(id);
                // return 타입이 달라서 Optional

            //2. 가져온 데이터를 모델에 등록
            model.addAttribute("article",articleEntity);

            //3. 보여줄 페이지를 설정
            return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {
        // 1. 모든 Article를 가져옴
        List<Article>articleEntityList = (List<Article>) articleRepository.findAll();
            // articleRepository의 반환타입을 List<Article>로 강제변형
        // 2.  가져온 Article 묶음을 뷰로 전달
        model.addAttribute("articleList", articleEntityList);

        // 3. 뷰 페이지를 설정
        return "articles/index"; //articles/index.mustache
    }
}
