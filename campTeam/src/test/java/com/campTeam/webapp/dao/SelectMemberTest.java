package com.campTeam.webapp.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class SelectMemberTest {
	
	@Autowired
	MemberJpaDAO2 memberJpaDAO2;
	
	@Test
	public void test() {
		
		log.info("MemberDTO2 : {}", memberJpaDAO2.findById("guest001"));
	}

}
