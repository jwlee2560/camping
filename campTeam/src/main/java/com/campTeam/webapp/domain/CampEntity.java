package com.campTeam.webapp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="camp_info_tbl")
@SequenceGenerator(
	    name = "CAMP_INFO_TBL_SEQ_GENERATOR",
	    sequenceName = "CAMP_INFO_TBL_SEQ",
	    initialValue = 1,
	    allocationSize = 1)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampEntity {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "CAMP_INFO_TBL_SEQ_GENERATOR")
	private int id;

	@Column(name="camp_name")
	private String campName;

	@Column(name="cate1")
	private String cate1;

	@Column(name="cate2")
	private String cate2;

	@Column(name="cate3")
	private String cate3;

	@Column(name="sido_name")
	private String sidoName;

	@Column(name="sigugun_name")
	private String sigugunName;

	@Column(name="eupmyundong_name")
	private String eupmyundongName;

	@Column(name="ry_name")
	private String ryName;

	@Column(name="bunji_name")
	private String bunjiName;

	@Column(name="road_name")
	private String roadName;

	@Column(name="building_num")
	private String buildingNum;

	@Column(name="latitude")
	private String latitude;

	@Column(name="longitude")
	private String longitude;

	@Column(name="zip")
	private String zip;

	@Column(name="road_address")
	private String roadAddress;

	@Column(name="jibun_address")
	private String jibunAddress;

	@Column(name="phone")
	private String phone;

	@Column(name="homepage")
	private String homepage;

	@Column(name="vendor")
	private String vendor;

	@Column(name="weekday_op_status")
	private String weekdayOpStatus;

	@Column(name="weekend_op_status")
	private String weekendOpStatus;

	@Column(name="spring_op_status")
	private String springOpStatus;

	@Column(name="summer_op_status")
	private String summerOpStatus;

	@Column(name="fall_op_status")
	private String fallOpStatus;

	@Column(name="winter_op_status")
	private String winterOpStatus;

	@Column(name="facil_electricity")
	private String facilElectricity;

	@Column(name="facil_hot_water")
	private String facilHotWater;

	@Column(name="facil_wifi")
	private String facilWifi;

	@Column(name="facil_campfire")
	private String facilCampfire;

	@Column(name="facil_trail")
	private String facilTrail;

	@Column(name="facil_pool")
	private String facilPool;

	@Column(name="facil_playground")
	private String facilPlayground;

	@Column(name="facil_mart")
	private String facilMart;

	@Column(name="facil_restroom")
	private String facilRestroom;

	@Column(name="facil_showerroom")
	private String facilShowerroom;

	@Column(name="facil_sink")
	private String facilSink;

	@Column(name="facil_extinguisher")
	private String facilExtinguisher;

	@Column(name="surr_facil_fishing")
	private String surrFacilFishing;

	@Column(name="surr_facil_trail")
	private String surrFacilTrail;

	@Column(name="surr_facil_beach")
	private String surrFacilBeach;

	@Column(name="surr_facil_maritime_leisure")
	private String surrFacilMaritimeLeisure;

	@Column(name="surr_facil_valley")
	private String surrFacilValley;

	@Column(name="surr_facil_stream")
	private String surrFacilStream;

	@Column(name="surr_facil_pool")
	private String surrFacilPool;

	@Column(name="surr_facil_youth_experience")
	private String surrFacilYouthExperience;

	@Column(name="surr_facil_rural_experience")
	private String surrFacilRuralExperience;

	@Column(name="surr_facil_childrens_play")
	private String surrFacilChildrensPlay;

	@Column(name="glam_bed")
	private String glamBed;

	@Column(name="glam_tv")
	private String glamTv;

	@Column(name="glam_freezer")
	private String glamFreezer;

	@Column(name="glam_internet")
	private String glamInternet;

	@Column(name="glam_restroom")
	private String glamRestroom;

	@Column(name="glam_aircon")
	private String glamAircon;

	@Column(name="glam_heater")
	private String glamHeater;

	@Column(name="glam_cookware")
	private String glamCookware;

	@Column(name="facil_characteristics")
	private String facilCharacteristics;

	@Column(name="facil_detail")
	private String facilDetail;

	@Column(name="reg_date")
	private String regDate;
	
	@Column(name="image")
	private String image;
	
	/** 네이버 별점 */
	@Column(name="avg_rating")
	private String avgRating;

	/** 네이버 리뷰(긍정지수) */
	@Column(name="review_positive")
	private String reviewPositive;

	/** 네이버 리뷰(부정지수) */
	@Column(name="review_negative")
	private String reviewNegative;
}
