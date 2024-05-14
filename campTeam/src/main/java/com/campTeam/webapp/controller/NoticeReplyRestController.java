package com.campTeam.webapp.controller;

//import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.campTeam.webapp.domain.NoticeVO;
import com.campTeam.webapp.service.NoticeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("notice")
@Slf4j
public class NoticeReplyRestController {
	
	@Autowired
	NoticeService noticeService; 

	@PostMapping("replyWrite.do")
	// public ResponseEntity<Boolean> replyWrite(@RequestBody Map<String, Object> map) {
	// 댓글을 작성하면서 즉시 현재까지의 댓글들 현황을 집계하여 리턴
	public ResponseEntity<List<NoticeVO>> replyWrite(@RequestBody Map<String, Object> map) {
		
		log.info("replyWrite.do : noticeNum={}, noticeContent={}", map.get("noticeNum"), map.get("noticeContent"));
		
		List<NoticeVO> replyList = new ArrayList<>();

		// ResponseEntity<Boolean> responseEntity = null; 
		ResponseEntity<List<NoticeVO>> responseEntity = null;
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
		
		try {
			NoticeVO noticeVO = new NoticeVO();
			
			//
			// 주의사항) 
			// 여기서 댓글의 고유 아이디는 DB를 통해서 생성되므로 원글의 아이디(noticeNum)는 다른 필드에 입력됩니다.
			noticeVO.setNoticeWriter(map.get("noticeWriter").toString());
			noticeVO.setNoticePass(map.get("noticePass").toString());
			noticeVO.setNoticeSubject("댓글");
			noticeVO.setNoticeContent(map.get("noticeContent").toString());
			noticeVO.setNoticeReRef(Integer.parseInt(map.get("noticeNum").toString()));
			noticeVO.setNoticeReLev(1);
			
			// 댓글의 현황을 보면서 댓글 시퀀스 결정
			noticeVO = noticeService.insertNotice(noticeVO);
			
			log.info("--- result : {}", noticeVO);
			
			if (noticeVO != null) {
				
				// 원글에 따른 전체 댓글 현황 목록(리스트) 가져오기 => 리턴 => Client(웹 브라우저)
				replyList = noticeService.selectReplysById(noticeVO.getNoticeReRef());				
				
				// 댓글 등록 성공 : 성공 코드(200)
				// responseEntity = new ResponseEntity<>(true, HttpStatus.OK);
				
				// 원글에 따른 전체 댓글 현황 목록(리스트) 리턴(클라리언트에 전송)
				responseEntity = new ResponseEntity<>(replyList, HttpStatus.OK); 
				
			} else {
				// 댓글 등록 실패 : 실패 코드(204)
				// responseEntity = new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
				responseEntity = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}
			
		} catch (Exception e) {
			log.error("replyWrite error : {}", e);
			e.printStackTrace();

			// 실패 코드(417) : 내부 서버 에러
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;		
	} //
	
	@GetMapping("getRepliesAll.do")
	public ResponseEntity<List<NoticeVO>> getRepliesAll(@RequestParam("noticeNum") int noticeNum) { 
		
		log.info("getRepliesAll.do : noticeNum={}", noticeNum);
		
		List<NoticeVO> replyList = new ArrayList<>();
		ResponseEntity<List<NoticeVO>> responseEntity = null;
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
		
		try {
			// 원글에 따른 전체 댓글 현황 목록(리스트) 가져오기 => 리턴 => Client(웹 브라우저)
			// 원글에 따른 전체 댓글 현황 목록(리스트) 리턴(클라리언트에 전송)
			replyList = noticeService.selectReplysById(noticeNum);				
			
			replyList.forEach(x-> { log.info("날짜 : " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(x.getNoticeDate())); });
			// replyList.forEach(x-> { log.info("날짜 : " + x.getNoticeDate()); });
			
			// 댓글들이 있다면...
			if (replyList.size() > 0) {

				// 댓글 등록 성공 : 성공 코드(200)
				// 원글에 따른 전체 댓글 현황 목록(리스트) 리턴(클라리언트에 전송)
				responseEntity = new ResponseEntity<>(replyList, HttpStatus.OK); 
				
			} else {
				// 댓글 등록 실패 : 실패 코드(204)
				responseEntity = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}
			
		} catch (Exception e) {
			log.error("getRepliesAll error : {}", e);
			e.printStackTrace();

			// 실패 코드(417) : 내부 서버 에러
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;		
	} //
	
	@PostMapping("replyUpdate.do")
	public ResponseEntity<List<NoticeVO>> replyUpdate(@RequestBody Map<String, Object> map) { 
		
		log.info("replyUpdate.do : noticeNum={}, noticeContent={}", map.get("noticeNum"), map.get("noticeContent"));
		
		List<NoticeVO> replyList = new ArrayList<>();

		ResponseEntity<List<NoticeVO>> responseEntity = null;
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
		
		try {
			NoticeVO noticeVO = new NoticeVO();
			
			int noticeNum = Integer.parseInt(map.get("noticeNum").toString());
			
			// 주의) 댓글 수정에서는 댓글의 아이디가 이미 등록시 발행이 되어 있으므로 댓글의 실제 아이디 !
			noticeVO.setNoticeNum(noticeNum);  
			noticeVO.setNoticeWriter(map.get("noticeWriter").toString());
			noticeVO.setNoticePass(map.get("noticePass").toString());
			noticeVO.setNoticeSubject("댓글");
			noticeVO.setNoticeReRef(Integer.parseInt(map.get("noticeReRef").toString()));
			noticeVO.setNoticeContent(map.get("noticeContent").toString());
			noticeVO.setNoticeReLev(1);
			noticeVO.setNoticeDate(new Date(System.currentTimeMillis()));
			
			log.info("noticeVO : {}", noticeVO);
			
			// 패쓰워드 체크
			String originalNoticePass = noticeService.selectNotice(noticeNum).getNoticePass();
			
			boolean isPass = map.get("noticePass").toString().equals(originalNoticePass) ? true : false;
			
			if (isPass == true) {
				
				noticeVO = noticeService.updateNotice(noticeVO);
				
				log.info("--- result : {}", noticeVO);
				
			} else { // 패쓰워드가 틀리면...
				
				log.error("게시글 패쓰워드 불일치");

				// Http Status Code : 401
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			} // 
			
			if (noticeVO != null) {
				
				// 원글에 따른 전체 댓글 현황 목록(리스트) 가져오기 => 리턴 => Client(웹 브라우저)
				replyList = noticeService.selectReplysById(noticeVO.getNoticeReRef());				
				
				// 댓글 등록 성공 : 성공 코드(200)
				// responseEntity = new ResponseEntity<>(true, HttpStatus.OK);
				
				// 원글에 따른 전체 댓글 현황 목록(리스트) 리턴(클라이언트에 전송)
				responseEntity = new ResponseEntity<>(replyList, HttpStatus.OK); 
				
			} else {
				// 댓글 등록 실패 : 실패 코드(204)
				// responseEntity = new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
				responseEntity = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}
			
		} catch (Exception e) {
			log.error("replyWrite error : {}", e);
			e.printStackTrace();

			// 실패 코드(417) : 내부 서버 에러
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;		
	} //
	
	@PostMapping("replyDelete.do")
	public ResponseEntity<List<NoticeVO>> replyDelete(@RequestBody Map<String, Object> map) { 
		
		log.info("replyDelete.do : noticeNum={}, originalNoticeNum={}, noticePass={}", 
					map.get("noticeNum"), map.get("originalNoticeNum"), map.get("noticePass"));
		
		List<NoticeVO> replyList = new ArrayList<>();

		ResponseEntity<List<NoticeVO>> responseEntity = null;
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
		
		int noticeNum = Integer.parseInt(map.get("noticeNum").toString());
		int originalNoticeNum = Integer.parseInt(map.get("originalNoticeNum").toString());
		String noticePass= map.get("noticePass").toString();
		
		try {
			
			// 패쓰워드 체크
			String originalNoticePass = noticeService.selectNotice(noticeNum).getNoticePass();
			
			log.info("originalNoticePass : {}", originalNoticePass);
			
			boolean isPass = noticePass.equals(originalNoticePass) ? true : false;
			
			log.info("패스워드 일치 여부 : {}", isPass);
			
			boolean result = false; // 삭제 결과
			
			if (isPass == true) {
				
				result = noticeService.deleteReplysById(noticeNum);
				
			} else { // 패쓰워드가 틀리면...
				
				log.error("게시글 패쓰워드 불일치");

				// Http Status Code : 401
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			} // 
			
			log.info("삭제 결과 : {}", result);
			
			if (result == true) { // 삭제
				
				// 원글에 따른 전체 댓글 현황 목록(리스트) 가져오기 => 리턴 => Client(웹 브라우저)
				replyList = noticeService.selectReplysById(originalNoticeNum);				
				
				// 댓글 등록 성공 : 성공 코드(200)
				// responseEntity = new ResponseEntity<>(true, HttpStatus.OK);
				
				// 원글에 따른 전체 댓글 현황 목록(리스트) 리턴(클라리언트에 전송)
				responseEntity = new ResponseEntity<>(replyList, HttpStatus.OK); 
				
			} else {
				// 댓글 등록 실패 : 실패 코드(204)
				// responseEntity = new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
				responseEntity = new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}
			
		} catch (Exception e) {
			log.error("replyWrite error : {}", e);
			e.printStackTrace();

			// 실패 코드(417) : 내부 서버 에러
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return responseEntity;		
	} //
	
}