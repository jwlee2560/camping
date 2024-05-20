package com.campTeam.webapp.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.campTeam.webapp.dao.CampDAOMyBatis;
import com.campTeam.webapp.domain.CampEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MapSearchController {
	
	@Autowired
	CampDAOMyBatis campDAO;
	
	@GetMapping("/mapSearch")
	public String mapSearch(@RequestParam("region") String region,
							@RequestParam("thema") String thema,
							@RequestParam("searchWord") String searchWord,
							Model model) {
		
		log.info("mapSearch");
		log.info("지역 : {}", region);
		log.info("테마 : {}", thema);
		log.info("searchWord : {}", searchWord);

		// 지역 판단
		List<String> regionList = new ArrayList<>();

		String regionFld = "";

		// 경기 북부
		if (region.equals("경기 북부")) {

			regionFld = "sigugun_name";
			regionList = Arrays.asList("연천군", "동두천시", "포천시", "파주시", "양주시", "의정부시", "구리시", "남양주시", "고양시", "가평군");

		} if (region.equals("경기 남부")) {
		// 경기 남부

			regionFld = "sigugun_name";
			regionList = Arrays.asList("안산시", "수원시", "용인시", "화성시", "성남시", "부천시", "평택시","안양시","시흥시","김포시","광주시","하남시","광명시","군포시","오산시","이천시","안성시","의왕시","양평군","여주시", "과천시");

		} 

		// 테마 적용
		String searchColumn = "";
		String searchColumnVal = "";

		switch (thema) {
			case "계곡" : searchColumn = "surr_facil_valley"; searchColumnVal = "주변 시설 물놀이(계곡)"; break;
			case "낚시" : searchColumn = "surr_facil_fishing"; searchColumnVal = "낚시 시설 있음"; break;
			case "놀이시설" : searchColumn = "surr_facil_childrens_play"; searchColumnVal = "주변 시설 어린이놀이시설"; break;
			case "수영장" : searchColumn = "surr_facil_pool"; searchColumnVal = "주변 시설 물놀이(수영장)"; break;
			case "산책로" : searchColumn = "surr_facil_trail"; searchColumnVal = "주변 시설 산책로"; break;
			case "수상레저" : searchColumn = "surr_facil_maritime_leisure"; searchColumnVal = "주변 시설 물놀이(수상레저)"; break;
			case "해당없음" : searchColumn = null; searchColumnVal = null; break;
		}

		// 질의(Query) 판정
		List<CampEntity> campList = null;

		try {

			campList = campDAO.getCampBySearching(regionFld, regionList, searchColumn, searchColumnVal, searchWord);
// 			log.info("campList size : {}", campList.size());

			if (campList.isEmpty() == true) {

				log.info("해당 정보가 없습니다.");

			} else { // 검색 결과 있음

				
				// JSON 문자열 생성 : 검색된 캠핑장 정보들을 json 문자열로 변환 
				ObjectMapper om = new ObjectMapper();
				try {

					String json = om.writeValueAsString(campList);
					model.addAttribute("campList", json);

				} catch (JsonProcessingException e) {
					log.error("json 생성 오류");
					e.printStackTrace();
				}

			} // 리스트 확보 여부

		} catch (Exception e) {
			log.error("DB 에러");
		}

		return "about";
	}


}
