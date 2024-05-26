package com.campTeam.webapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.campTeam.webapp.domain.CampEntity;
import com.campTeam.webapp.domain.CampEntity2;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class CampingServiceTest {
	
	@Autowired
	CampingService campingService;
	
	@Test
	public void test() {
		
		CampEntity campEntity = campingService.recommendCamp("가래골농원 캠핑장");
		
		log.info("campEntity : {}", campEntity);
		
		assertEquals("0", campEntity.getAvgRating());
		assertEquals("18", campEntity.getReviewPositive());
		assertEquals("12", campEntity.getReviewNegative());
		
	}
	
	@Test
	public void test2() {
		
		CampEntity campEntity = campingService.recommendCamp("(주)수동자연마을 힐링별밤수목원캠핑");
		
		log.info("campEntity : {}", campEntity);
		
		assertEquals("0", campEntity.getAvgRating());
		assertEquals("18", campEntity.getReviewPositive());
		assertEquals("12", campEntity.getReviewNegative());
	}

}
