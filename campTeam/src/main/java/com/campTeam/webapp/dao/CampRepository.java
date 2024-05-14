package com.campTeam.webapp.dao;

import org.springframework.data.repository.CrudRepository;

import com.campTeam.webapp.domain.CampEntity;

public interface CampRepository extends CrudRepository<CampEntity, Integer> {

}
