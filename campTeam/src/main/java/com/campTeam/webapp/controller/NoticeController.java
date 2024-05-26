package com.campTeam.webapp.controller;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.campTeam.webapp.domain.NoticeDTO;
import com.campTeam.webapp.domain.NoticeVO;
import com.campTeam.webapp.service.NoticeService;
import com.campTeam.webapp.service.FileUploadService;
import com.campTeam.webapp.service.ImageService;
import com.campTeam.webapp.util.FileUploadUtil;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("notice")
@Slf4j
public class NoticeController { 
	
	@Autowired
	NoticeService noticeService;
	
	@Autowired
	FileUploadService fileUPloadService;
	
	@Value("${spring.servlet.multipart.max-file-size}") 
	String uploadFileMaxSize;
		
	@GetMapping("/write.do")
	public String write(Model model) {
		
		model.addAttribute("noticeDTO", new NoticeDTO());
		return "/notice/write";
	} //
	
	// DTO 대신 Map 형태의 인자 수신 
	@PostMapping("/writeProc.do")
	public String writeProc(@RequestParam Map<String, Object> map, 
							@RequestPart(value="noticeFile") MultipartFile noticeFile, 
							Model model) {
		
		log.info("공지글 쓰기 인자 전송 현황 : ");
		map.entrySet().forEach(arg -> {
			log.info("{}", arg);
		});
		
		NoticeVO noticeVO = new NoticeVO(map, noticeFile); // Map → VO
		
		// 첨부 파일이 있다면...				
		String msg = ""; // 메시지
		
		if (noticeFile.isEmpty() == false) {
			
			log.info("공지글 작성 처리(첨부 파일) : {}", noticeFile.getOriginalFilename());
			
			String actualUploadFilename = FileUploadUtil.encodeFilename(noticeFile.getOriginalFilename());
			noticeVO.setNoticeFile(actualUploadFilename);

			// 첨부 파일이 있을 때만 저장
			msg = fileUPloadService.storeUploadFile(noticeVO.getNoticeNum(), noticeFile, noticeVO.getNoticeFile());
			log.info("msg : {}", msg);
		} 
		
		log.info("NoticeVO : {}", noticeVO);
		
		noticeVO = noticeService.insertNotice(noticeVO);
		
		log.info("----- 공지글 저장 NoticeVO : {}", noticeVO);
		
		if (noticeVO != null) {
			msg = "공지글 저장에 성공하였습니다.";
		}
			
		// TODO
		// /error/error
		// errMsg, movePage = /notice/list.do"
		// 정상 : 파일이 업로드 되었습니다.
		
		model.addAttribute("errMsg", msg);
		model.addAttribute("movePage", "/notice/list.do"); 
		
		return "/error/error"; 
	} //
	
	@GetMapping("/view.do/{noticeNum}")
	public String view(@PathVariable("noticeNum") int noticeNum, Model model) {
		
		NoticeVO noticeVO =noticeService.selectNotice(noticeNum);
		log.info("NoticeVO : {}", noticeVO);
		
		model.addAttribute("notice", noticeVO);
		
		// 조회할 때마다 조회수 갱신(+)
		noticeService.updateNoticeReadcountByNoticeNum(noticeNum);
		
		return "/notice/view";
	}
	
}