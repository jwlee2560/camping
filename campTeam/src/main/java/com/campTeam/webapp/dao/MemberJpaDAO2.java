package com.campTeam.webapp.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.campTeam.webapp.domain.MemberDTO;
import com.campTeam.webapp.domain.MemberDTO2;
import com.campTeam.webapp.domain.MemberVO;

public interface MemberJpaDAO2 
	extends PagingAndSortingRepository<MemberDTO2, String> {
	// extends CrudRepository<MemberVO, String> {
	
	
	@Query(value="select m.id as id,"
				+ "m.password as password,"
				+ "m.name as name,"
				+ "m.gender as gender,"
				+ "m.age as age,"
				+ "m.email as email,"
				+ "m.mobile as mobile,"
				+ "m.phone as phone,"
				+ "m.zip as zip,"
				+ "m.road_Address,"
				+ "m.jibun_Address,"
				+ "m.detail_Address,"
				+ "m.birthday as birthday,"
				+ "m.joindate as joindate,"
				+ "m.enabled as enabled,"
				+ " r.role as role "
				+ "from member_tbl m,user_roles r "
				+ "where m.id = r.username "
				+ "and m.id = :id", nativeQuery = true)
	public MemberDTO2 findById(@Param("id") String id);
	
}