CREATE SEQUENCE notice_seq
START WITH 1
INCREMENT BY 1
MAXVALUE 99999
NOCYCLE;
​
CREATE TABLE notice_tbl (
	notice_num NUMBER DEFAULT 0,
	notice_writer VARCHAR2(30) NOT NULL,
	notice_pass VARCHAR2(20) NOT NULL,
	notice_subject VARCHAR2(100 char) NOT NULL,
	notice_content CLOB NOT NULL,
	notice_original_file NVARCHAR2(200),
	notice_file NVARCHAR2(200),
	notice_re_ref NUMBER NOT NULL,
	notice_re_lev NUMBER NOT NULL,
	notice_re_seq NUMBER NOT NULL,
	notice_readcount NUMBER DEFAULT 0,
	notice_date DATE,
	PRIMARY KEY(notice_num)
);

comment ON COLUMN notice_tbl.notice_num IS '공지글 번호';
comment ON COLUMN notice_tbl.notice_writer IS '공지글 작성자';
comment ON COLUMN notice_tbl.notice_pass IS '공지글 비밀번호';
comment ON COLUMN notice_tbl.notice_subject IS '공지글 제목';
comment ON COLUMN notice_tbl.notice_content IS '공지글 내용';
comment ON COLUMN notice_tbl.notice_original_file IS '공지글 첨부 파일(원본)';
comment ON COLUMN notice_tbl.notice_file IS '공지글 첨부 파일(암호화)';
comment ON COLUMN notice_tbl.notice_re_ref IS '공지글 답글의 원 공지글(관련글) 번호';
comment ON COLUMN notice_tbl.notice_re_lev IS '공지글 답글 레벨';
comment ON COLUMN notice_tbl.notice_re_seq IS '공지글 답글 순서';
comment ON COLUMN notice_tbl.notice_readcount IS '공지글 조회수';
comment ON COLUMN notice_tbl.notice_date IS '공지글 작성일자';
​