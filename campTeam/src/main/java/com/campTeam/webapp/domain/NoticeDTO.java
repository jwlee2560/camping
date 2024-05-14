package com.campTeam.webapp.domain;

import java.sql.Date;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class NoticeDTO {
	
	private int noticeNum; // 게시글 번호
	private String noticeWriter; // 게시글 작성자
	private String noticePass; // 게시글 비밀번호
	private String noticeSubject; // 게시글 제목
	private String noticeContent; // 게시글 내용
	private MultipartFile noticeFile; // 게시글 첨부 파일
	private int noticeReRef; // 게시글 답글의 원 게시글(관련글) 번호
	private int noticeReLev; // 게시글 답글 레벨
	private int noticeReSeq; // 게시글 답글 순서
	private int noticeReadCount = 0; // 게시글 조회수
	private Date noticeDate; // 게시글 작성일자
	private String textMulti = "text"; // 텍스트 모드(text:기본값) / 멀티미디어 모드(multi)
	
	// 업로드 파일(파일명을 확인할 수 있도록 파일명 인쇄) : noticeFile.getOriginalFilename()
	@Override
	public String toString() {
		return "NoticeDTO [noticeNum=" + noticeNum + ", noticeWriter=" + noticeWriter + ", noticePass=" + noticePass
				+ ", noticeSubject=" + noticeSubject + ", noticeContent=" + noticeContent + ", noticeFile=" + noticeFile.getOriginalFilename()
				+ ", noticeReRef=" + noticeReRef + ", noticeReLev=" + noticeReLev + ", noticeReSeq=" + noticeReSeq
				+ ", noticeReadCount=" + noticeReadCount + ", noticeDate=" + noticeDate + ", textMulti=" + textMulti + "]";
	}

}