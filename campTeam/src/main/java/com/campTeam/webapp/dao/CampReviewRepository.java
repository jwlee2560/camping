package com.campTeam.webapp.dao;

import org.springframework.data.repository.CrudRepository;
import com.campTeam.webapp.domain.CampReviewVO;

public interface CampReviewRepository extends CrudRepository<CampReviewVO, String> {

}