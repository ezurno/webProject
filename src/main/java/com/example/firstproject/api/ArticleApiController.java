package com.example.firstproject.api;

import com.example.firstproject.Service.ArticleService;
import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j // log 사용
@RestController //RestAPI 용 컨트롤러, 데이터를 JSON으로 반환
public class ArticleApiController {


//    @Autowired // 외부에서 가져옴, 자동 연결
//    private ArticleRepository articleRepository;
    //GET
//    @GetMapping("/api/articles")
//    public List<Article> index() {
//        return (List<Article>) articleRepository.findAll();
//    }
//
//    @GetMapping("/api/articles/{id}")
//    public Article index(@PathVariable Long id) { // 하나만 가져올때
//        return articleRepository.findById(id).orElse(null);
//    }
//
//    //POST
//
//    @PostMapping("/api/articles")
//    public Article create(@RequestBody ArticleForm dto) { //RequestBody >> json 에서 데이터 받아오기
//        Article article = dto.toEntity();
//        return articleRepository.save(article);
//    }
//
//    @PatchMapping("/api/articles/{id}")
//    public ResponseEntity<Article> patch(@PathVariable Long id, @RequestBody ArticleForm dto) {
//
//        // Response 상태코드를 반환 할 수 있는 ResponseEntity
//        // 1. 수정용 Entity 생성
//        Article article = dto.toEntity();
//        log.info("id : {} , article : {}", id, article.toString());
//
//        // 2. 대상 Entity 조회
//        Article target = articleRepository.findById(id).orElse(null);
//
//        // 3. 잘못된 요청 처리 (대상이 없거나 id 가 다른 경우)
//        if (target == null || id != article.getId()) {
//            //400. 잘못된 요청 응답!
//            log.info("잘못된 요청! id : {}, article : {}", id, article.toString());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // 잘못되면 오류 전송
//        }
//        // 4. 정상 업데이트 및 정상 응답(200)
//
//        target.patch(article); // Article Entity에 patch함수 생성 *없는 부분 null값 되는것을 방지
//
//        Article updated = articleRepository.save(target);
//        return ResponseEntity.status(HttpStatus.OK).body(updated); // HttpStatus가 맞으면 body를 업데이트
//    }
//
//    //DELETE
//
//    @DeleteMapping("/api/articles/{id}")
//    public ResponseEntity<Article> delete(@PathVariable Long id) {
//        //대상 찾기
//        Article target = articleRepository.findById(id).orElse(null);
//
//        // 잘못된 요청 처리
//        if (target == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//        //대상 삭제
//        articleRepository.delete(target);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }
//
}
