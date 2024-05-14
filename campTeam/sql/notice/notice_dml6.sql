-- 공지글의 댓글이 존재하는지 여부 검색
-- ex) 공지글 삭제시 댓글이 있으면 삭제하지 못하도록 조치
-- ex) 공지글 원글 아이디 : 126
SELECT count(*) FROM notice_tbl 
WHERE notice_re_ref = 126;

-- 조회수 갱신
UPDATE notice_tbl SET
notice_readcount = notice_readcount + 1
WHERE notice_num = 107;