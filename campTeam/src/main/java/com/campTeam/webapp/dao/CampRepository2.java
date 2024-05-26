package com.campTeam.webapp.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.campTeam.webapp.domain.CampEntity;
import com.campTeam.webapp.domain.CampEntity2;

public interface CampRepository2 extends CrudRepository<CampEntity2, Integer> {
	
	Optional<CampEntity2> findByCampName(String campName);

}
