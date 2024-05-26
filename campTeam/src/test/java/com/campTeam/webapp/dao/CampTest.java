package com.campTeam.webapp.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.campTeam.webapp.domain.CampEntity;
import com.campTeam.webapp.domain.CampEntity2;
import com.campTeam.webapp.domain.CampReviewVO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class CampTest {
	
	@Autowired
	CampRepository2 campRepo;
	
	@Autowired
	CampDAOMyBatis campDAO;

	@Autowired
	CampReviewRepository campReviewRepo;
	
	@Test
	public void test() {
		
		List<CampReviewVO> reviewList = null;

		// 10개만 한정 선정
		CampEntity2 campEntity = campRepo.findByCampName("열두달캠핑장").get();
		log.info("campEntity: {}", campEntity);
		
		reviewList = campReviewRepo.findAllByCampName("열두달캠핑장");
		log.info("reviewListSize: {}", reviewList.size());

	}

}
