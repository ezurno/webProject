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

    @Autowired // 외부에서 가져옴
    private ArticleService articleService;
//    @Autowired // 외부에서 가져옴, 자동 연결
//    private ArticleRepository articleRepository;
    //GET
    @GetMapping("/api/articles")
    public List<Article> index() {
        return (List<Article>) articleService.index();
    } // articleRepository 를 aritcleService로 변경 및 index 함수 사용
//
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) { // 하나만 가져올때
        return articleService.show(id);
    }
//
    //POST
//
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto) { //RequestBody >> json 에서 데이터 받아오기
        Article created = articleService.create(dto);
        return (created!=null)?
                ResponseEntity.status(HttpStatus.OK).body(created):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
//
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
        Article updated = articleService.update(id, dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status((HttpStatus.BAD_REQUEST)).build();

    }
    //DELETE

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article deleted = articleService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //트랜잭션 transacion -> 실패시 롤백 !
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos) {
        // <<List<Article>>인 이유는 2중 리스트 이기 떄문.
        // ex) 리스트 ( 리스트(예약 날짜) 리스트 (예약 시간) . . . )
        List<Article> createdList = articleService.createArticles(dtos);


        return (createdList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
