package com.campTeam.webapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campTeam.webapp.dao.MemberJpaCustomDAO;
import com.campTeam.webapp.dao.MemberJpaDAO;
import com.campTeam.webapp.dao.MemberJpaDAO2;
import com.campTeam.webapp.dao.MemberJpaRoleDAO;
import com.campTeam.webapp.domain.MemberDTO;
import com.campTeam.webapp.domain.MemberDTO2;
import com.campTeam.webapp.domain.MemberRoleVO;
import com.campTeam.webapp.domain.MemberVO;
import com.campTeam.webapp.domain.Role;
import com.campTeam.webapp.domain.RoleVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberJpaService {

//	@Autowired
//	MemberJpaDAO2 memberJpaDAO2;
	
	@Autowired
	MemberJpaDAO memberJpaDAO;
	
	@Autowired
	MemberJpaRoleDAO memberJpaRoleDAO;
	
	@Autowired
	MemberJpaCustomDAO memberJpaCustomDAO; 
	
	@Transactional(readOnly = true)
	public MemberDTO selectMember(String id) {
		return memberJpaDAO.findById(id);
	}
	
	@Transactional
	public boolean insertMemberRole(MemberVO memberVO) {
	
		boolean result = false;
		
		try {
			memberJpaDAO.save(memberVO);
			result = true;
		} catch (Exception e) {
			log.error("MemberJpaService.insertMemberRole.insertMember : {}", e);
			e.printStackTrace();
		}
		
		result = false;
		
		try {
			RoleVO role = new RoleVO();
			role.setUsername(memberVO.getId());
			role.setRole("ROLE_USER");
			memberJpaRoleDAO.save(role);
			
			result = true;
		} catch (Exception e) {
			log.error("MemberJpaService.insertMemberRole.insertRole : {}", e);
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Transactional(readOnly = true)
	public boolean hasFld(String fld, String val) {
		
		boolean result = false;
		
		int temp = fld.equals("id") ? memberJpaDAO.countById(val) :
				   fld.equals("email") ? memberJpaDAO.countByEmail(val) :
				   memberJpaDAO.countByMobile(val);
		
		result = temp == 1 ? true : false;
		
		return result;
	}
	
	@Transactional(readOnly = true)
	public boolean hasFldForUpdate(String id, String fld, String val) {
		
		boolean result = false;
		
		int temp = fld.equals("email") ? memberJpaDAO.countEmailByEmail(id, val) :
				   memberJpaDAO.countMobileByMobile(id, val);
		
		result = temp == 1 ? true : false;
		
		return result;
	}
	
	@Transactional(readOnly = true)
	public int selectMembersCount() {
		return memberJpaDAO.count();
	}
	
	@Transactional
	public boolean updateMember(MemberVO memberVO) {
		
		boolean result = false;
		
		try {
			memberJpaDAO.save(memberVO);
			result = true;
		} catch (Exception e) {
			log.error("MemberJpaService.updateMember : {}", e);
			e.printStackTrace();
		}
			
		return result;
	} //
	
	@Transactional
	public boolean updateRoles(String id, boolean roleUserYn, boolean roleAdminYn) {

		boolean result = false;
		
		// 먼저 해당 id의 등급(role)이 있는지 점검
		// 있으면 삽입하지 않고 그대로 두고, 없으면 롤(role) 삽입
		
		// 경우의 수 : 대부분 회원(ROLE_USER) 이상의 롤을 보유하고 있기 때문에 이런 경우는
		// 관리자 여부를 우선 점검하는 것이 유효함.
		
		List<RoleVO> roleList = memberJpaRoleDAO.findByUsername(id);
		List<String> roles = new ArrayList<>();
		
		for (RoleVO role : roleList) {
			roles.add(role.getRole());
		}
		
		// 회원(ROLE_USER)이면서 관리자 권한이 없는 경우
		if (roleAdminYn == true && 
			roles.contains("ROLE_USER") == true && 
			roles.contains("ROLE_ADMIN") == false) 
		{
			log.info("관리자 권한 할당");
			
			RoleVO role = new RoleVO();
			role.setUsername(id);
			role.setRole("ROLE_ADMIN");
			
			result = this.insertRole(role);
		}
		// 회원(ROLE_USER)이면서 관리자 권한을 회수할 경우(관리자 권한 삭제)
		else if (roleAdminYn == false && 
				 roles.contains("ROLE_USER") == true && 
				 roles.contains("ROLE_ADMIN") == true) 
		{
			log.info("관리자 권한 회수");	
			
			String role = "ROLE_ADMIN";
			result = this.deleteRoleById(id, role);
		}
				
		return result;
	}
	
	@Transactional
	public boolean insertRole(RoleVO roleVO) {
		
		boolean result = false;
		
		try {
			memberJpaRoleDAO.save(roleVO);
			result = true;
		} catch (Exception e) {
			result = false;
			log.error("MemberJpaService.insertRole : {}", e);
			e.printStackTrace();
		} //	
			
		return result;
	}
	
	@Transactional
	public boolean deleteRoleById(String id, String role) {
		
		boolean result = false;
		
		RoleVO roleVO = new RoleVO();
		roleVO.setUsername(id);
		roleVO.setRole(role);
		
		try {
			memberJpaRoleDAO.delete(roleVO);
			result = true;
		} catch (Exception e) {
			log.error("MemberService.deleteRoleById : {}", e);
			e.printStackTrace();
		}
			
		return result;
	}
	
	@Transactional(readOnly = true)
	public List<MemberDTO> selectMembersByPaging(int page, int limit) {
		
		Pageable pageable = PageRequest.of(page-1, limit, Sort.by(Direction.DESC, "id"));
		return memberJpaDAO.findAll(pageable).getContent();
	} //
	
	@Transactional(readOnly = true)
	public List<Map<String, Object>> selectMembersMapWithRolesBySearching(int page, int limit, String searchKey, String searchWord) {
		
		log.info("searchKey : {}", searchKey);
		log.info("searchWord : {}", searchWord);
		
		// return memberJpaCustomDAO.findAllMapBySearching(page, limit, searchKey, searchWord);
		return memberJpaCustomDAO.findAllObjectMapBySearching(page, limit, searchKey, searchWord);
	} //
	
	@Transactional(readOnly = true)
	public List<MemberRoleVO> selectMembersVOWithRolesBySearching(int page, int limit, String searchKey, String searchWord) {
		
		log.info("searchKey : {}", searchKey);
		log.info("searchWord : {}", searchWord);
		
		return memberJpaCustomDAO.findAllVOBySearching(page, limit, searchKey, searchWord);
	} //
	
}