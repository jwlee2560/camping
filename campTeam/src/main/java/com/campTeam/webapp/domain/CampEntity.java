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
	
	/** 캠핑장 아이디  */
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "CAMP_INFO_TBL_SEQ_GENERATOR")
	private int id;
	
	/** 캠핑장명  */
	@Column(name="camp_name")
	private String campName;
	
	/** 카테고리1  */
	@Column(name="cate1")
	private String cate1;
	
	/** 카테고리2  */
	@Column(name="cate2")
	private String cate2;

	/** 카테고리3  */
	@Column(name="cate3")
	private String cate3;

	/** 시도 명칭 */
	@Column(name="sido_name")
	private String sidoName;

	/** 시군구 명칭 */
	@Column(name="sigugun_name")
	private String sigugunName;

	/** 법정읍면동 */
	@Column(name="eupmyundong_name")
	private String eupmyundongName;

	/** 리 명칭 */
	@Column(name="ry_name")
	private String ryName;

	/** 번지 */
	@Column(name="bunji_name")
	private String bunjiName;

	/** 도로명 이름 */
	@Column(name="road_name")
	private String roadName;

	/** 건물 번호 */
	@Column(name="building_num")
	private String buildingNum;

	/** 위도 */
	@Column(name="latitude")
	private String latitude;

	/** 경도 */
	@Column(name="longitude")
	private String longitude;

	/** 우편번호 */
	@Column(name="zip")
	private String zip;

	/** 도로명주소 */
	@Column(name="road_address")
	private String roadAddress;

	/** 지번주소 */
	@Column(name="jibun_address")
	private String jibunAddress;

	/** 전화번호 */
	@Column(name="phone")
	private String phone;

	/** 홈페이지 */
	@Column(name="homepage")
	private String homepage;

	/** 사업주체 */
	@Column(name="vendor")
	private String vendor;

	/** 평일 운영 여부 */
	@Column(name="weekday_op_status")
	private String weekdayOpStatus;

	/** 주말 운영 여부 */
	@Column(name="weekend_op_status")
	private String weekendOpStatus;

	/** 봄 운영 여부 */
	@Column(name="spring_op_status")
	private String springOpStatus;

	/** 여름 운영 여부 */
	@Column(name="summer_op_status")
	private String summerOpStatus;

	/** 가을 운영 여부 */
	@Column(name="fall_op_status")
	private String fallOpStatus;

	/** 겨울 운영 여부 */
	@Column(name="winter_op_status")
	private String winterOpStatus;

	/** 부대시설 전기 */
	@Column(name="facil_electricity")
	private String facilElectricity;

	/** 부대시설 온수 */
	@Column(name="facil_hot_water")
	private String facilHotWater;

	/** 부대시설 무선인터넷 */
	@Column(name="facil_wifi")
	private String facilWifi;

	/** 부대시설 장작판매 */
	@Column(name="facil_campfire")
	private String facilCampfire;

	/** 부대시설 산책로 */
	@Column(name="facil_trail")
	private String facilTrail;

	/** 부대시설 물놀이장 */
	@Column(name="facil_pool")
	private String facilPool;

	/** 부대시설 놀이터 */
	@Column(name="facil_playground")
	private String facilPlayground;

	/** 부대시설 마트 */
	@Column(name="facil_mart")
	private String facilMart;

	/** 부대시설 화장실 수 */
	@Column(name="facil_restroom")
	private String facilRestroom;

	/** 부대시설 샤워실 수 */
	@Column(name="facil_showerroom")
	private String facilShowerroom;

	/** 부대시설 씽크대 수 */
	@Column(name="facil_sink")
	private String facilSink;

	/** 부대시설 소화기 수 */
	@Column(name="facil_extinguisher")
	private String facilExtinguisher;

	/** 주변 시설 낚시 */
	@Column(name="surr_facil_fishing")
	private String surrFacilFishing;

	/** 주변 시설 산책로 */
	@Column(name="surr_facil_trail")
	private String surrFacilTrail;

	/** 주변 시설 물놀이(해수욕) */
	@Column(name="surr_facil_beach")
	private String surrFacilBeach;

	/** 주변 시설 물놀이(수상레저) */
	@Column(name="surr_facil_maritime_leisure")
	private String surrFacilMaritimeLeisure;

	/** 주변 시설 물놀이(계곡) */
	@Column(name="surr_facil_valley")
	private String surrFacilValley;

	/** 주변 시설 물놀이(강) */
	@Column(name="surr_facil_stream")
	private String surrFacilStream;

	/** 주변 시설 물놀이(수영장) */
	@Column(name="surr_facil_pool")
	private String surrFacilPool;

	/** 주변 시설 청소년체험시설 */
	@Column(name="surr_facil_youth_experience")
	private String surrFacilYouthExperience;

	/** 주변 시설 농어촌체험시설 */
	@Column(name="surr_facil_rural_experience")
	private String surrFacilRuralExperience;

	/** 주변 시설 어린이놀이시설 */
	@Column(name="surr_facil_childrens_play")
	private String surrFacilChildrensPlay;

	/** 글램핑 침대 */
	@Column(name="glam_bed")
	private String glamBed;

	/** 글램핑 티비 */
	@Column(name="glam_tv")
	private String glamTv;

	/** 글램핑 냉장고 */
	@Column(name="glam_freezer")
	private String glamFreezer;

	/** 글램핑 유무선인터넷 */
	@Column(name="glam_internet")
	private String glamInternet;

	/** 글램핑 내부화장실 */
	@Column(name="glam_restroom")
	private String glamRestroom;

	/** 글램핑 에어컨 */
	@Column(name="glam_aircon")
	private String glamAircon;

	/** 글램핑 난방기구 */
	@Column(name="glam_heater")
	private String glamHeater;

	/** 글램핑 취사도구 */
	@Column(name="glam_cookware")
	private String glamCookware;

	/** 시설 특징 */
	@Column(name="facil_characteristics")
	private String facilCharacteristics;

	/** 시설 소개 */
	@Column(name="facil_detail")
	private String facilDetail;

	/** 최종작성일 */
	@Column(name="reg_date")
	private String regDate;
	
	/** 캠핑장 이미지 */
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

