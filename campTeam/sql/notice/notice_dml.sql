-- 공지글 작성(삽입)
INSERT INTO notice_tbl VALUES
(notice_seq.NEXTVAL, 'abcd1234', '#Abcd1234', '글 제목', '글 내용', NULL, NULL, 0, 0, 0, 0, sysdate);

-- 가장 최근의 게시판 시퀀스값 조회 
SELECT notice_seq.nextval FROM dual;

-- 공지글 조회
-- 페이징 : 페이지 단위로 일부분 레코들을 추출
-- 현재 페이지(page) = 1, 5개의 레코드씩 조회
SELECT *  
FROM (SELECT m.*,  
             FLOOR((ROWNUM - 1) / 5 + 1) page  
      FROM (
             SELECT *
			 FROM notice_tbl
             ORDER BY notice_num DESC
           ) m  
      )  
WHERE page = 1;

-- 전체 공지글 수 조회
SELECT count(*) FROM notice_tbl;

-- 공지글 조회수 갱신
UPDATE notice_tbl SET 
notice_readcount=(SELECT notice_readcount 
				 FROM notice_tbl 
				 WHERE notice_num=15) + 1
WHERE notice_num=15;

-- 공지글 조회수 조회
SELECT notice_readcount 
FROM notice_tbl 
WHERE notice_num=15;


-- 검색 : 공지글 조회
-- 페이징 : 페이지 단위로 일부분 레코들을 추출
-- 현재 페이지(page) = 1, 5개의 레코드씩 조회
-- 글 제목(키워드) 검색
SELECT *  
FROM (SELECT m.*,  
             FLOOR((ROWNUM - 1) / 5 + 1) page  
      FROM (
             SELECT *
			 FROM notice_tbl
			 WHERE notice_subject LIKE '%날씨%'
             ORDER BY notice_num DESC
           ) m  
      )  
WHERE page = 1;


-- 글 내용(키워드) 검색
SELECT *  
FROM (SELECT m.*,  
             FLOOR((ROWNUM - 1) / 5 + 1) page  
      FROM (
             SELECT *
			 FROM notice_tbl
			 WHERE notice_content LIKE '%과일%'
             ORDER BY notice_num DESC
           ) m  
      )  
WHERE page = 1;

-- 작성자(키워드) 검색
SELECT *  
FROM (SELECT m.*,  
             FLOOR((ROWNUM - 1) / 5 + 1) page  
      FROM (
             SELECT *
			 FROM notice_tbl
			 WHERE notice_writer = 'abcd1234'
             ORDER BY notice_num DESC
           ) m  
      )  
WHERE page = 1;

-- 검색 레코드 수
-- ex) 글 내용(키워드) 검색
SELECT count(*)  
FROM notice_tbl
WHERE notice_content LIKE '%과일%';


-- 검색 예시
-- 검색키 : 내용
-- 검색어 : 과일
-- 정렬 기준 : 조회수
-- 정렬 순서 : 오름차순
SELECT *  
FROM (SELECT m.*,  
             FLOOR((ROWNUM - 1) / 5 + 1) page  
      FROM (
             SELECT *
			 FROM notice_tbl
			 WHERE notice_content LIKE '%과일%'
             ORDER BY notice_readcount ASC
           ) m  
      )  
WHERE page = 3;


-- 검색 예시
-- 검색키 : 제목
-- 검색어 : 운동
-- 정렬 기준 : 작성일
-- 정렬 순서 : 내림차순
SELECT *  
FROM (SELECT m.*,  
             FLOOR((ROWNUM - 1) / 5 + 1) page  
      FROM (
             SELECT *
			 FROM notice_tbl
			 WHERE notice_subject LIKE '%운동%'
             ORDER BY notice_date DESC
           ) m  
      )  
WHERE page = 1;
