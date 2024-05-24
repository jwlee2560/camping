package com.campTeam.webapp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.campTeam.webapp.domain.CampReviewVO;

public interface CampReviewRepository extends CrudRepository<CampReviewVO, String> {
	
	List<CampReviewVO> findAllByCampName(String campName);

}