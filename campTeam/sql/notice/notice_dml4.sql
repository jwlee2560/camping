-- 공지글 수정
UPDATE notice_tbl SET 
notice_subject = '갱신한 글제목',
notice_content = '갱신한 글내용',
notice_original_file = '',
notice_file = '',
notice_date = sysdate
WHERE notice_num = 15;


UPDATE notice_tbl SET 
notice_subject = '갱신한 글제목2',
notice_content = '갱신한 글내용2',
notice_original_file = '',
notice_file = '',
notice_date = sysdate
WHERE notice_num = 234;

-- 댓글 수정
UPDATE notice_tbl SET 
notice_content = '갱신한 댓글내용',
notice_date = sysdate
WHERE notice_num = 141 
and notice_writer = 'proiject11';

-- 댓글 삭제
DELETE notice_tbl WHERE notice_num=375;

-- 원글/댓글 동시 삭제
DELETE notice_tbl WHERE notice_num=126 OR notice_re_ref=126;