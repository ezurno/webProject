package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j // log를 사용하기 위해 어노테이션
public class ArticleController {

    @Autowired // 스프링 부트가 미리 생성해놓은 객체를 가져다가 자동연결 원래
    private ArticleRepository articleRepository;// 자바에는 밑에 repositou에 new로 객체를 생성해야되지만 알아서 생성

    @Autowired
    private CommentService commentService; // commentService를 쓰기위해 선언

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

        return "redirect:/articles/" + saved.getId();
        //redirect 적용, getId()함수를 만들기 위해 Article Entity로 이동
    }

    @GetMapping("/articles/{id}") /*@PathVariable url Path로부터 입력이된다.  */
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);

            //1. id로 데이터를 가져옴
            Article articleEntity = articleRepository.findById(id).orElse(null);
                // id값을 통해 찾을 때 값이 없으면 null이 담겨짐
            //Optional<Article> articleEntity = articleRepository.findById(id);
                // return 타입이 달라서 Optional

            // commentDtos를 모델에 등록하지 않아서 댓글이 안나오므로
            // controller에 모델의 데이터를 입력해야됨.
            List<CommentDto> commentDtos = commentService.comments(id);


            //2. 가져온 데이터를 모델에 등록
            model.addAttribute("article",articleEntity);
            model.addAttribute("commentDtos",commentDtos); // 댓글을 등록

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

    @GetMapping("/articles/{id}/edit") // 여기선 중괄호 하나만!!
    //*** {article.id}지만 edit함수의 매개변수 Long id 와 같아야 하므로 articles.를 빼준다.
    public String edit(@PathVariable Long id, Model model) {
        //수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //만약 없다면 null로 반환

        //모델에 데이터 등록
        model.addAttribute("article", articleEntity);
        
        //뷰 페이지 설정
        return"articles/edit";//해당 페이지가 getMapping으로 요청되면 edit을 반환한다.
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form) {
        log.info(form.toString());//제대로 데이터를 받았는지 확인

        //1. DTO를 Entity로 변환
        Article articleEntity = form.toEntity();
        form.toEntity(); //ArticleForm에 만들어 놓은 toEntity함수

        //2. Entity를 DB로 저장
            //2-1. 수정
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
            //만약에 없다면 null 리턴 함수 orElse
            //2-2. 기존 데이터 값을 갱신
        if (target != null) {
            articleRepository.save(articleEntity);
        }
        //3. 수정결과를 페이지로 리다이렉트
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete") //공식적으로 지원을 안하므로
        // Get방식을 사용하기 위해 DeleteMapping이 아닌 Get
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        //RedirectAttributes rttr로 메세지를 보내줌
        log.info("삭제 명령이 내려졌음.");

        //1. 삭제 대상을 기재 Target으로 지정
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());
        //2. 대상 삭제
        if (target != null) {
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제가 완료되었습니다.");
            //메세지 출력 함수 addFlashAttribute
        }
        //3. 결과 페이지로 리다이렉트
        return "redirect:/articles";
    }
}
