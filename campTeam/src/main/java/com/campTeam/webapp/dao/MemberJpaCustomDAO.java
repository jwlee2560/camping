package com.campTeam.webapp.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.query.ResultListTransformer;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.thymeleaf.spring6.expression.Fields;

import com.campTeam.webapp.domain.MemberRoleVO;
import com.campTeam.webapp.domain.MemberVO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class MemberJpaCustomDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	// case-1 : ClassCastException 발생 
	public List<Map<String, Object>> findAllMapBySearching(int page, int limit, String searchKey, String searchWord) {
		
		List<Map<String, Object>> list = null;
		
		String searchPhrase = "";
		
		if (searchKey.equals("address")) {
			
			searchPhrase = "AND road_address like '%" + searchWord + "%' "
						 + "OR jibun_address like '%" + searchWord + "%' "
					  	 + "OR detail_address like '%" + searchWord + "%'";
			
		} else if (searchKey.equals("name") && !searchKey.equals("address")) {
			
			searchPhrase = "AND name like '%" + searchWord + "%'";
			
		} else if (!searchKey.equals("name") && !searchKey.equals("address")) {
			
			searchPhrase = "AND name = '" + searchWord + "'";
		}
		
		log.info("searchPhrase : {}", searchPhrase);
		
		String sql = "SELECT    id, "
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
				+ "		             FLOOR((ROWNUM - 1) / ? + 1) page   "
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
				+ searchPhrase
				+ "		             ORDER BY id DESC "
				+ "		          ) m   "
				+ "		      )   "
				+ "		WHERE page = ?";
		
		try {
			
			Query query = entityManager.createNativeQuery(sql);
			list = query.setParameter(1, limit)
					 	.setParameter(2, page)
					 	.unwrap(org.hibernate.query.Query.class)			
					 	.setResultListTransformer(new ResultListTransformer<Map<String, Object>>() {

							@Override
							public List<Map<String, Object>> transformList(List<Map<String, Object>> resultList) {
								
								return resultList;
							}
					 		
					 	})
					 	.getResultList();
				        
			
			log.info("###################################################");
			
			list.get(0).keySet().forEach(x -> { log.info("{}", x); } );
			
		} catch (Exception e) {
			log.error("MemberJpaCustomDAO.findAllMapBySearching : " + e);
		}
		
		return list;
	} //
	
	// case-2
	public List<Map<String, Object>> findAllObjectMapBySearching(int page, int limit, String searchKey, String searchWord) {
		
		List<Map<String, Object>> result = new ArrayList<>();
		
		List<Object[]> list = null;
		
		String searchPhrase = "";
		
		if (searchKey.equals("address")) {
			
			searchPhrase = "AND road_address like '%" + searchWord + "%' "
						 + "OR jibun_address like '%" + searchWord + "%' "
					  	 + "OR detail_address like '%" + searchWord + "%'";
			
		} else if (searchKey.equals("name") && !searchKey.equals("address")) {
			
			searchPhrase = "AND name like '%" + searchWord + "%'";
			
		} else if (!searchKey.equals("name") && !searchKey.equals("address")) {
			
			searchPhrase = "AND name = '" + searchWord + "'";
		}
		
		log.info("searchPhrase : {}", searchPhrase);
		
		String sql = "SELECT    id, "
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
				+ "		             FLOOR((ROWNUM - 1) / ? + 1) page   "
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
				+ searchPhrase
				+ "		             ORDER BY id DESC "
				+ "		          ) m   "
				+ "		      )   "
				+ "		WHERE page = ?";
		
		try {
			
			Query query = entityManager.createNativeQuery(sql, Object[].class);
			
			list = query.setParameter(1, limit)
					 	.setParameter(2, page)
					 	.getResultList();
			
			int count = 0;
			
//			list.forEach(x -> {
//				
//				Map<String, Object> map = new HashMap<>();
//				map.put(count + "", x);
//				result.add(map);
//				count++;
//			});
			
			System.out.println("###########################################################");
			
			Field[] superFlds = MemberVO.class.getDeclaredFields();
			Field[] flds = MemberRoleVO.class.getDeclaredFields();
			
			List<String> fldList = new ArrayList<>();
			
			for (int i=0; i<superFlds.length; i++) {
				
				if (superFlds[i].getName().equals("log") == false) {
					fldList.add(superFlds[i].getName());
				}
			}
			
			for (int i=0; i<flds.length; i++) {				
				fldList.add(flds[i].getName());
			}
			
			// log, serialVersionUID(직렬화) 필드는 미포함 되어야 함. serialVersionUID(직렬화)는 배제 안됨 => 별도 조치
			fldList.remove("log");
			fldList.remove("serialVersionUID");
		
			log.info("flds 길이 : " + superFlds.length);
			
//			for (String fld : fldList) {
//				log.info("필드명 : {}", fld);
//			}
			
			Map<String, Object> map = null;
			
			for (int j=0; j<list.size(); j++) {
			
				for (int i=0; i<fldList.size(); i++) {
	
					map = new HashMap<String, Object>();
					map.put(fldList.get(i), list.get(j)[i]);
					result.add(map);
				}
			
				System.out.println("--------------------------------------");
			}	
			
		} catch (Exception e) {
			log.error("MemberJpaCustomDAO.findAllObjectBySearching : " + e);
		}
		
		return result;
	} //
	
	// case-3
	public List<MemberRoleVO> findAllVOBySearching(int page, int limit, String searchKey, String searchWord) {
		
		List<MemberRoleVO> list = null;
		
		String searchPhrase = "";
		
		if (searchKey.equals("address")) {
			
			searchPhrase = "AND road_address like '%" + searchWord + "%' "
						 + "OR jibun_address like '%" + searchWord + "%' "
					  	 + "OR detail_address like '%" + searchWord + "%'";
			
		} else if (searchKey.equals("name") && !searchKey.equals("address")) {
			
			searchPhrase = "AND name like '%" + searchWord + "%'";
			
		} else if (!searchKey.equals("name") && !searchKey.equals("address")) {
			
			searchPhrase = "AND name = '" + searchWord + "'";
		}
		
		log.info("searchPhrase : {}", searchPhrase);
		
		String sql = "SELECT    id, "
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
				+ "		             FLOOR((ROWNUM - 1) / ? + 1) page   "
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
				+ searchPhrase
				+ "		             ORDER BY id DESC "
				+ "		          ) m   "
				+ "		      )   "
				+ "		WHERE page = ?";
		
		try {
			
			Query query = entityManager.createNativeQuery(sql, MemberRoleVO.class);
			
			list = query.setParameter(1, limit)
					 	.setParameter(2, page)
				        .getResultList();
			
		} catch (Exception e) {
			log.error("MemberJpaCustomDAO.findAllVOBySearching : " + e);
		}
		
		return list;
	} //
	
} //