INSERT INTO article(id ,title, content) VALUES (1, '가가가가' , '11111');
INSERT INTO article(id ,title, content) VALUES (2, '나나나나' , '22222');
--Value 값을 직접입력하면 서버를 켤 때마다 고정된 데이터로 나옴

INSERT INTO article(id , title, content) VALUES (3, '당신의 인생영화는?', '댓글 ㄱ');
INSERT INTO article(id , title, content) VALUES (4, '당신의 소울푸드는?', '댓글 ㄱㄱ');
INSERT INTO article(id , title, content) VALUES (5, '당신의 취미는?', '댓글 ㄱㄱㄱ');
-- article 더미데이터

-- 댓글 더미 데이터
-- 3번 게시글 답글
INSERT INTO comment(id , article_id, nickname, body) VALUES (1, 3, 'PARK', '괴물');
INSERT INTO comment(id , article_id, nickname, body) VALUES (2, 3, 'KIM', '혹성탈출');
INSERT INTO comment(id , article_id, nickname, body) VALUES (3, 3, 'LEE', '쇼생크 탈출');

-- 4번 게시글 답글
INSERT INTO comment(id , article_id, nickname, body) VALUES (4, 4, 'PARK', '치킨');
INSERT INTO comment(id , article_id, nickname, body) VALUES (5, 4, 'KIM', '샤브샤브');
INSERT INTO comment(id , article_id, nickname, body) VALUES (6, 4, 'LEE', '짜장면');

-- 5번 게시글 답글
INSERT INTO comment(id , article_id, nickname, body) VALUES (7, 5, 'PARK', '조깅');
INSERT INTO comment(id , article_id, nickname, body) VALUES (8, 5, 'KIM', '유튜브');
INSERT INTO comment(id , article_id, nickname, body) VALUES (9, 5, 'LEE', '독서');
