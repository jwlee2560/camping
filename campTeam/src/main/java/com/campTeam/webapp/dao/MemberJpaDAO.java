package com.campTeam.webapp.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.campTeam.webapp.domain.MemberVO;

public interface MemberJpaDAO 
	extends PagingAndSortingRepository<MemberVO, String> {
	// extends CrudRepository<MemberVO, String> {
	
	public MemberVO save(MemberVO memberVO);

	public MemberVO findById(String id);
	
	// public boolean hasFld(String fld, String val) {
	// SELECT count(*) FROM member_tbl WHERE ${fld} = #{val}
	public int countById(String id);
	public int countByEmail(String email);
	public int countByMobile(String mobile);
		
	//select u from User u where u.firstname like %?1
	/*
	 @Query(value = "SELECT * FROM registered_exam_tbl "  
					 + "WHERE reg_id in (SELECT reg_exam_id FROM exam_member_tbl "  
					 + "                 WHERE member_id = :memberId AND exam_pass_ok = 0)", 
			   nativeQuery=true)
		List<RegisteredExamVO> findAllById(@Param("memberId") String memberId); 
	 */
	@Query(value="SELECT COUNT(*) FROM ( "
			+ "		        SELECT ID FROM member_tbl "
			+ "		        WHERE ID != :id "
			+ "		    ) "
			+ "		WHERE ID = :id", nativeQuery = true)
	public int countIdById(@Param("id") String id);
	
	@Query(value="SELECT COUNT(*) FROM ( "
			+ "		        SELECT EMAIL FROM member_tbl "
			+ "		        WHERE ID != :id "
			+ "		    ) "
			+ "		WHERE EMAIL = :email", nativeQuery = true)
	public int countEmailByEmail(@Param("id") String id, 
							     @Param("email") String email);
	
	@Query(value="SELECT COUNT(*) FROM ( "
			+ "		        SELECT MOBILE FROM member_tbl "
			+ "		        WHERE ID != :id "
			+ "		    ) "
			+ "		WHERE MOBILE = :mobile", nativeQuery = true)
	public int countMobileByMobile(@Param("id") String id, 
							       @Param("mobile") String mobile);
	
	int count();
	
	// Page<BoardVO> findAll(Pageable pageable);
	Page<MemberVO> findAll(Pageable pageable);
	
	/*
	@Query(value="SELECT    id, "
			+ "				password, "
			+ "				name,	 "
			+ "				gender, "
			+ "				age, "
			+ "				email, "
			+ "				mobile, "
			+ "				phone, "
			+ "				zip, "
			+ "				road_address, "
			+ "				jibun_address, "
			+ "				detail_address, "
			+ "				birthday, "
			+ "				joindate,		 "
			+ "				enabled, "
			+ "				role, "
			+ "		        page    "
			+ "		FROM (SELECT m.*,   "
			+ "		             FLOOR((ROWNUM - 1) / :limit + 1) page   "
			+ "		      FROM ( "
			+ "			      	 SELECT  "
			+ "			      	 DISTINCT m.*, "
			+ "					 ( "
			+ "					    SELECT LISTAGG(r2.role, ',') WITHIN GROUP (ORDER BY m2.id)  "
			+ "					    FROM member_tbl m2, user_roles r2   "
			+ "					    WHERE r2.username = m2.id "
			+ "					    AND r2.username = m.id "
			+ "					 ) AS \"ROLE\"            "
			+ "					 FROM member_tbl m, user_roles r "
			+ "					 WHERE m.id = r.username "
			+ :searchPhrase
			+ "		             ORDER BY id DESC "
			+ "		          ) m   "
			+ "		      )   "
			+ "		WHERE page = :page", nativeQuery=true)
	List<Map<String, Object>> findAllBySearching(@Param("searchPhrase") String searchPhrase,
												 @Param("page") int page, 
												 @Param("limit") int limit);
	*/
	
}