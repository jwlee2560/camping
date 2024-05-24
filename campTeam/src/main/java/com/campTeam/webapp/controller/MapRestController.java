package com.campTeam.webapp.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.campTeam.webapp.dao.CampDAOMyBatis;
import com.campTeam.webapp.domain.CampEntity;
import com.campTeam.webapp.service.CampingService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MapRestController {

	@Autowired
	CampingService campService;

	@GetMapping("/mapRest")
	public ResponseEntity<Object> mapRestService(@RequestParam("region") String region,
												 @RequestParam("thema") String thema,
												 @RequestParam("searchWord") String searchWord) {

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

		// 응답(response) 정보
		ResponseEntity<Object> response = null;

		try {
			//10개만 추출
//			campList = campDAO.getCampBySearching(regionFld, regionList, searchColumn, searchColumnVal, searchWord);
//			campList = campDAO.getCampBySearching(regionFld, regionList, searchColumn, searchColumnVal, searchWord)
//			campList = campDAO.getTotalCampBySearching(regionFld, regionList, searchColumn, searchColumnVal, searchWord)
//					  .stream()
//					  .limit(10)
//					  .toList();
// 			log.info("campList size : {}", campList.size());

			// 별점, 긍정/부정 지수 추가
			campList = campService.recommendCamps(regionFld, regionList, searchColumn, searchColumnVal, searchWord);

			if (campList.isEmpty() == true) {

				log.info("해당 정보가 없습니다.");
				response = new ResponseEntity<>("해당 정보가 존재하지 않습니다.", HttpStatus.NO_CONTENT);

			} else { // 검색 결과 있음

				response = new ResponseEntity<>(campList, HttpStatus.OK);

			} // 리스트 확보 여부

		} catch (Exception e) {
			log.error("DB 에러");
			response = new ResponseEntity<>("서버 응답에 문제가 있습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		log.info("response : " + response.getStatusCode());

		return response;
	} //

}