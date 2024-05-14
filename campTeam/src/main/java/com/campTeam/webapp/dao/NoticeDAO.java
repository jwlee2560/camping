package com.campTeam.webapp.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.campTeam.webapp.domain.NoticeVO;

// public interface NoticeDAO extends JpaRepository<NoticeVO, Integer>{
// 페이징 메서드 추출위해 Repository 교체
public interface NoticeDAO extends PagingAndSortingRepository<NoticeVO, Integer>{
	
	NoticeVO save(NoticeVO noticeVO);
	
	long count();

	Page<NoticeVO> findAll(Pageable pageable);
	
	NoticeVO findById(int noticeNum);
	
	int countByNoticeSubjectLike(String noticeSubject); // Like
	int countByNoticeSubjectContaining(String noticeSubject); // Containing
	int countByNoticeContentContaining(String noticeContent);
	int countByNoticeWriterContaining(String noticeWriter);
	
	Page<NoticeVO> findByNoticeSubjectLike(String noticeSubject, Pageable pageable); // Like
	Page<NoticeVO> findByNoticeSubjectContaining(String noticeSubject, Pageable pageable); // Containing
	Page<NoticeVO> findByNoticeContentContaining(String noticeContent, Pageable pageable);
	Page<NoticeVO> findByNoticeWriterContaining(String noticeWriter, Pageable pageable);
	
	// 원글에 따른 소속 댓글들 가져오기
	List<NoticeVO> findByNoticeReRef(int noticeReRef); 
	
	// 댓글 제외한 원글들만의 게시글 수 : noticeReRef = 0
	long countByNoticeReRef(int noticeReRef);
	
	// 댓글 제외한 원글들만의 게시글들만 가져오기(페이징) : noticeReRef = 0
	Page<NoticeVO> findByNoticeReRef(int noticeReRef, Pageable pageable); 
	
	// 게시글 조회수 갱신
	@Modifying
	@Query(value = "UPDATE notice_tbl SET " + 
				   "notice_readcount = notice_readcount + 1 " + 
				   "WHERE notice_num = :noticeNum", nativeQuery = true)
	void updateNoticeReadcountByNoticeNum(@Param("noticeNum") int noticeNum);

	// 게시글 삭제
	void deleteById(int noticeNum);
}