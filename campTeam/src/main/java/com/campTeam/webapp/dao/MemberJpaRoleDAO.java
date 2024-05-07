package com.campTeam.webapp.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.campTeam.webapp.domain.RoleVO;

public interface MemberJpaRoleDAO 
		extends CrudRepository<RoleVO, String>{
	
	List<RoleVO> findByUsername(String username);

}