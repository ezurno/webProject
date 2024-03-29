package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j // log 사용
@Service // 서비스 선언 (서비스 객체 스프링부트에 생성)
public class ArticleService {
    @Autowired // DI
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return (List<Article>) articleRepository.findAll(); // 강제 형변환 , Repository 에서 DB 가져옴
    }

    public Article show (Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if (article.getId() != null) {
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        //1. 수정할 데이터 정보
        Article article = dto.toEntity();
        log.info("id : {} , article {}", id, article.toString());

        //2. 대상 엔티티 찾기
        Article target = articleRepository.findById(id).orElse(null);

        //3. 잘못된 요청 처리 (대상이 없거나 id가 다른경우)
        if (target == null || id != article.getId() || (article.getTitle() == null && article.getContent() == null)) { // day 5 - test작동시 오류 해결, null 값이 안떠서 수정


            log.info("잘못된 요청 ! id : {}, article : {}", id , article.toString());
            return null;
        }

        //4. 업데이트
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
    }

    public Article delete(Long id) {
        //대상 찾기
        Article target = articleRepository.findById(id).orElse(null);

        // 잘못된 요청 처리
        if (target == null) {
            return null;
        }

        //대상 삭제
        articleRepository.delete(target);
        return target;
    }

    @Transactional // 해당 메소드를 트랜잭션으로 묶는다
    public List<Article> createArticles(List<ArticleForm> dtos) {
        //dto 묶음을 Entity 묶음으로 변환
        List<Article> articleList =dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());
        //stream문법이므로 나중에 알려줌
        // List<Article> articleList = new ArrayList<>();
        // for (int i = 0; i < dtos.size(); i++) {
        //  ArticleForm dto = dtos.get(i);
        //  Article entity = dto.toEntity();
        //  articleList.add(entity);
        // 위의 stream문법을 for문으로 쓰면 이렇게 됨


        //entity 묶음을 DB로 저장
        articleList.stream()
                .forEach(article -> articleRepository.save(article));

        //강제 예외 발생
        articleRepository.findById(-1L).orElseThrow(
                ()-> new IllegalArgumentException("결제 실패!")
        );

        //결과값 변환

        return articleList;
    }


}
