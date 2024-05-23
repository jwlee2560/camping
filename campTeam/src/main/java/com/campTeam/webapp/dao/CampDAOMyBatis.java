package com.campTeam.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.campTeam.webapp.domain.CampEntity;

@Mapper
public interface CampDAOMyBatis {

	// https://mybatis.org/mybatis-3/ko/dynamic-sql.html#script
	@Select({"<script>",
		     "SELECT * FROM camp_info_tbl ",
			 "WHERE ${regionColumn} IN ",
			 "		<foreach item='region' index='index' collection='regionList' ",
			 "			     open='(' separator=',' close=')' nullable='true'>",
			 "			#{region} ",
			 "		</foreach> ",
			 "		<if test='searchColumn != null'>",
			 "			AND ${searchColumn} = #{searchColumnVal}",
			 "		</if> ",
			 "		<if test='searchWord != null'>",
			 "			AND (road_address like '%${searchWord}%' or jibun_address like '%${searchWord}%')",
			 "		</if>",
			 "</script>"})
	public List<CampEntity> getCampBySearching(@Param("regionColumn") String regionColumn,
											   @Param("regionList") List<String> regionList,
											   @Param("searchColumn") String searchColumn,
											   @Param("searchColumnVal") String searchColumnVal,
											   @Param("searchWord") String searchWord);
	
	@Select({"<script>",
		     "SELECT cit.CAMP_NAME, cit.CAMP_INTRO_IMAGES, cit.LATITUDE, ",
		     "		 cit.LONGITUDE, cit.ROAD_ADDRESS, cit.JIBUN_ADDRESS, cit.FACIL_DETAIL, cgt.IMAGE ",
		     "FROM camp_info_tbl cit, CAMPING_GROUNDS_TABLE cgt ",
			 "WHERE cit.CAMP_NAME = cgt.NAME ",
			 "AND ${regionColumn} IN ",
			 "		<foreach item='region' index='index' collection='regionList' ",
			 "			     open='(' separator=',' close=')' nullable='true'>",
			 "			#{region} ",
			 "		</foreach> ",
			 "		<if test='searchColumn != null'>",
			 "			AND ${searchColumn} = #{searchColumnVal}",
			 "		</if> ",
			 "		<if test='searchWord != null'>",
			 "			AND (road_address like '%${searchWord}%' or jibun_address like '%${searchWord}%')",
			 "		</if>",
			 "</script>"})
	public List<CampEntity> getTotalCampBySearching(@Param("regionColumn") String regionColumn,
												   @Param("regionList") List<String> regionList,
												   @Param("searchColumn") String searchColumn,
												   @Param("searchColumnVal") String searchColumnVal,
												   @Param("searchWord") String searchWord);

}