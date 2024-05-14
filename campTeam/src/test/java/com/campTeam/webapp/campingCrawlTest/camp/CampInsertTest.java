package com.campTeam.webapp.campingCrawlTest.camp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.campTeam.webapp.dao.CampRepository;
import com.campTeam.webapp.domain.CampEntity;
import com.campTeam.webapp.service.CampingService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class CampInsertTest {
	
	@Autowired
	CampingService campingService;

	@Autowired
	CampRepository campRepo;

	@Test
	public void test() {

		log.info("데이터 크기 : {}", campingService.loadCampData().size());

		for (CampEntity camp : campingService.loadCampData()) {

			 campRepo.save(camp);
		} //

	} //

}
