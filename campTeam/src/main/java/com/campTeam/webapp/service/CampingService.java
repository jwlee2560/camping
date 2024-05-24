package com.campTeam.webapp.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.campTeam.webapp.dao.CampDAOMyBatis;
import com.campTeam.webapp.dao.CampRepository;
import com.campTeam.webapp.dao.CampReviewRepository;
import com.campTeam.webapp.domain.CampEntity;
import com.campTeam.webapp.domain.CampReviewVO;
import com.campTeam.webapp.domain.UserRequestVO;
import com.campTeam.webapp.domain.UserResultVO;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CampingService {

	@Autowired
	CampRepository campRepo;

	@Autowired
	CampDAOMyBatis campDAO;

	@Autowired
	CampReviewRepository campReviewRepo;

	private static final int EARTH_RADIUS = 6371; // 지구 반경(km)

	private static final double LATITUDE_DEGREE_PER_METER = 1.0 / (2 * Math.PI * EARTH_RADIUS * 1000 / 360);

	public static double getDistance(double lat1, double lon1, double lat2, double lon2) {

		double p1 = lat1 * Math.PI / 180;
		double p2 = lat2 * Math.PI / 180;
		double gp = (lat2 - lat1) * Math.PI / 180;
		double gl = (lon2 - lon1) * Math.PI / 180;

		double a = Math.sin(gp / 2) * Math.sin(gp / 2) +
				   Math.cos(p1) * Math.cos(p2) *
				   Math.sin(gl / 2) * Math.sin(gl / 2);

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		double d = EARTH_RADIUS * 1000 * c;

		return d; // meter
	}

    // 두 좌표 사이의 거리를 구하는 함수
    // getdistance(첫번째 좌표의 위도, 첫번째 좌표의 경도, 두번째 좌표의 위도, 두번째 좌표의 경도)
    public static double getDistance2(double lat1, double lon1, double lat2, double lon2) {

        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) +
        			  Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515 * 1609.344;

        return dist; // 단위 meter
    }

	// This function converts decimal degrees to radians
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	// This function converts radians to decimal degrees
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}

	public double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
	    double latDiff = lat2 - lat1;
	    double lonDiff = lon2 - lon1;
	    return Math.sqrt(latDiff * latDiff + lonDiff * lonDiff);
	}

	// Haversine Formula(하버사인) 공식
	// 지국가 완전 구라는 전제하에 위도 1도의 거리 계산
	// 위도 1도의 거리 = 2 * Math.PI * (지구의 원주) * cos(위도) / 360
	// 참고) https://jinkpark.tistory.com/296

	// 특정 반경 위도 범위 측정
	public double[] getLatitudeRange(double latitude, int radiusInMeters) {

	    double degreeRange = radiusInMeters * LATITUDE_DEGREE_PER_METER;

	    double minLatitude = latitude - degreeRange;
	    double maxLatitude = latitude + degreeRange;

	    return new double[]{minLatitude, maxLatitude};
	}

	// 특정 반경 경도 범위 측정
	public double[] getLongitudeRange(double latitude, double longitude, int radiusInMeters) {

		double longitudeDegreePerMeter = 360 / (2 * Math.PI * EARTH_RADIUS * 1000 * Math.cos(Math.toRadians(latitude)));
	    double degreeRange =  longitudeDegreePerMeter * radiusInMeters;

	    double minLongitude = longitude - degreeRange;
	    double maxLongitude = longitude + degreeRange;

	    return new double[]{minLongitude, maxLongitude};
	}

	public List<CampEntity> loadCampData() {

		List<CampEntity> campings = new ArrayList<>();
		String filename = "한국문화정보원_전국 문화 여가 활동 시설(캠핑) 데이터_20221130.CSV";

		String[] campInfo;
		CampEntity campEntity;

		try {

			ClassPathResource resource = new ClassPathResource("/csv/" + filename);

			CSVReader csvReader
				= new CSVReader(new InputStreamReader(new FileInputStream(resource.getFile()), "EUC-KR"));

			csvReader.readNext(); // 컬럼명 제외

			while ((campInfo = csvReader.readNext()) != null) {

				// log.info("campName :{}", campInfo[0]);

				campEntity = CampEntity.builder()
										.campName(campInfo[0])
										.cate1(campInfo[1])
										.cate2(campInfo[2])
										.cate3(campInfo[3])
										.sidoName(campInfo[4])
										.sigugunName(campInfo[5])
										.eupmyundongName(campInfo[6])
										.ryName(campInfo[7])
										.bunjiName(campInfo[8])
										.roadName(campInfo[9])
										.buildingNum(campInfo[10])
										.latitude(campInfo[11])
										.longitude(campInfo[12])
										.zip(campInfo[13])
										.roadAddress(campInfo[14])
										.jibunAddress(campInfo[15])
										.phone(campInfo[16])
										.homepage(campInfo[17])
										.vendor(campInfo[18])
										.weekdayOpStatus(campInfo[19])
										.weekendOpStatus(campInfo[20])
										.springOpStatus(campInfo[21])
										.summerOpStatus(campInfo[22])
										.fallOpStatus(campInfo[23])
										.winterOpStatus(campInfo[24])
										.facilElectricity(campInfo[25])
										.facilHotWater(campInfo[26])
										.facilWifi(campInfo[27])
										.facilCampfire(campInfo[28])
										.facilTrail(campInfo[29])
										.facilPool(campInfo[30])
										.facilPlayground(campInfo[31])
										.facilMart(campInfo[32])
										.facilRestroom(campInfo[33])
										.facilShowerroom(campInfo[34])
										.facilSink(campInfo[35])
										.facilExtinguisher(campInfo[36])
										.surrFacilFishing(campInfo[37])
										.surrFacilTrail(campInfo[38])
										.surrFacilBeach(campInfo[39])
										.surrFacilMaritimeLeisure(campInfo[40])
										.surrFacilValley(campInfo[41])
										.surrFacilStream(campInfo[42])
										.surrFacilPool(campInfo[43])
										.surrFacilYouthExperience(campInfo[44])
										.surrFacilRuralExperience(campInfo[45])
										.surrFacilChildrensPlay(campInfo[46])
										.glamBed(campInfo[47])
										.glamTv(campInfo[48])
										.glamFreezer(campInfo[49])
										.glamInternet(campInfo[50])
										.glamRestroom(campInfo[51])
										.glamAircon(campInfo[52])
										.glamHeater(campInfo[53])
										.glamCookware(campInfo[54])
										.facilCharacteristics(campInfo[55])
										.facilDetail(campInfo[56])
										.regDate(Date.valueOf("2024-05-09").toString()) // 등록일은 임의 일자로 생성
										.build();

				campings.add(campEntity);

			} // while

		} catch (IOException e) {
			log.error("readCampingsFromCSV : {}", e);
			e.printStackTrace();
		} catch (CsvValidationException e) {
			log.error("readCampingsFromCSV : {}", e);
			e.printStackTrace();
		}

		log.info("camping size : {}", campings.size());

		return campings;
	}

	/**
	 * 계절 판정
	 *
	 * @param campEntity
	 * @param date
	 * @return
	 */
	private boolean checkEnableSeason(CampEntity campEntity, LocalDate date) {

		boolean flag = false;

		int month = date.getMonthValue();

		flag = month >= 3 && month <= 5 ? campEntity.getSpringOpStatus().equals("봄 운영") :
			   month >= 6 && month <= 8 ? campEntity.getSummerOpStatus().equals("여름 운영") :
			   month >= 9 && month <= 11 ? campEntity.getFallOpStatus().equals("가을 운영") :
			   campEntity.getWinterOpStatus().equals("겨울 운영");

		return flag;
	}

	/**
	 * 만족도 계산
	 *
	 * @param userResult
	 * @return
	 */
	private int calcSatisfaction(UserResultVO userResult) {

		log.info("userResult : {}", userResult);

		int result = 0;
		int totalFactor = 11; // 총 만족도 조사 요건 ex) 지역, ..., 낚시 가능 여부
		int factorSum = 1; // 사용자 요청 지역과 일치한다는 전제로 +1로 시작

		factorSum = userResult.getEnableOps().contains("운영 가능") ? factorSum++ : factorSum;

		factorSum += userResult.getIsElectricity();
		factorSum += userResult.getIsWifi();
		factorSum += userResult.getIsCampfire();
		factorSum += userResult.getIsHeater();
		factorSum += userResult.getIsPool();
		factorSum += userResult.getIsPlayground();
		factorSum += userResult.getIsTrail();
		factorSum += userResult.getIsMaritimeLeisure();
		factorSum += userResult.getIsFishing();

		log.info("factorSum : {}", factorSum);
		log.info("totalFactor : {}", totalFactor);

		result = (int)((float)factorSum / (float)totalFactor * 100);

		log.info("만족도 : " + result);

		return result;
	}

	/**
	 * 개인별 캠프 추천
	 *
	 * @param userRequestVO
	 * @return
	 */
	public List<UserResultVO> predictCamp(UserRequestVO userRequestVO) {

		List<UserResultVO> recommList = new ArrayList<>();
		List<CampEntity> legacyCampList = (List<CampEntity>) campRepo.findAll();

		// log.info("기존 캠프장 리스트 수 : {}", legacyCampList.size());

		// 행선지 및 일정 우선적으로 검색 => 기타 부대/주변 시설 여부와 비교하여 추천 리스트 확보
		String dest = userRequestVO.getDestination();
		log.info("행선 예정지 : {}", dest);

		// filter 메서드에서 null 값 점검 주의 !
		legacyCampList = legacyCampList.stream()
									   .filter(x -> (x.getSigugunName() != null && (x.getSidoName().contains(dest) || x.getSigugunName().contains(dest))))
									   .toList();

		UserResultVO userResult = null;

		// 나머지 요구사항 반영 비교
		for (CampEntity campEntity : legacyCampList) {

			log.info("for문 진입");

			userResult = new UserResultVO();

			// 행선 시기 가능 여부
			boolean enableOps = this.checkEnableSeason(campEntity, userRequestVO.getTime());

			String opsMsg = userRequestVO.getTime().format(DateTimeFormatter.ofPattern("YYYY년 MM월 dd일"))
						  + (enableOps == true ? "운영 가능" : "운영 불가");

			userResult.setEnableOps(opsMsg);

			// 부대/주변 시설 여부 점검
			userResult.setIsElectricity(campEntity.getFacilElectricity().equals("전기 사용가능") ? 1 : 0);
			userResult.setIsWifi(campEntity.getFacilWifi().equals("wifi 사용가능") ? 1 : 0);
			userResult.setIsCampfire(campEntity.getFacilCampfire().equals("장작판매") ? 1 : 0);
			userResult.setIsHeater(campEntity.getFacilHotWater().equals("온수 사용가능") ? 1 : 0);
			userResult.setIsPool(campEntity.getFacilPool().equals("물놀이장 보유") ? 1 : 0);
			userResult.setIsPlayground(campEntity.getFacilPlayground().equals("놀이터 보유") ? 1 : 0);
			userResult.setIsTrail(campEntity.getFacilTrail().equals("산책로 있음") ? 1 : 0);
			userResult.setIsMaritimeLeisure(campEntity.getSurrFacilMaritimeLeisure().equals("시설 주변 물놀이(수상레저) 있음") ? 1 : 0);
			userResult.setIsFishing(campEntity.getSurrFacilFishing().equals("낚시 시설 있음") ? 1 : 0);

			// 기타 스펙
			userResult.setCampId(campEntity.getId());
			userResult.setCampName(campEntity.getCampName());
			userResult.setUserId(userRequestVO.getUserId());
			userResult.setDestination(userRequestVO.getDestination());

			// 만족도 계산
			int satisfaction = this.calcSatisfaction(userResult);

			userResult.setSatisfaction(satisfaction);

			recommList.add(userResult);

			log.info("for문 next");

		} // for

		log.info("recommList size : {}", recommList.size());

		return recommList;
	} //

	/**
	 * 개인별 캠핑 추천(3조)
	 *
	 * @param regionColumn 지역 필드  ex) 경기 북부
	 * @param regionList (실제)지역 ex) "연천군", "동두천시", "포천시", "파주시", "양주시", "의정부시", "구리시", "남양주시", "고양시", "가평군"
	 * @param searchColumn  검색 필드  ex) surr_facil_fishing
	 * @param searchColumnVal  검색 필드 값  ex) 낚시 시설 있음
	 * @param searchWord 검색어  ex) 가평
	 *
	 * @return
	 */
	public List<CampEntity> recommendCamps(String regionColumn, List<String> regionList,
			   							   String searchColumn, String searchColumnVal, String searchWord) {

		List<CampEntity> campList = new ArrayList<>();
		List<CampReviewVO> reviewList = null;

		// 10개만 한정 선정
		campList = campDAO.getTotalCampBySearching(regionColumn, regionList, searchColumn, searchColumnVal, searchWord)
						  .stream()
						  .limit(10)
					  	  .toList();

		for (CampEntity campEntity : campList) {

			// 네이버 별점 및 긍정/부정 지수
			if (searchWord.equals("") == true) {

				reviewList = (List<CampReviewVO>) campReviewRepo.findAll();

			} else if (searchWord != null) {

				reviewList = campReviewRepo.findAllByCampName(searchWord);

			} // if

			String avgRating = ""; // 네이버 별점
			int positiveDegree = 0; // 긍정 지수
			int negativeDegree = 0; // 부정 지수

			if (reviewList.size() > 0) {

				avgRating = (reviewList.get(0).getAvgRating() == null || reviewList.get(0).getAvgRating().equals("")) ?
						    "별점정보 없음" : reviewList.get(0).getAvgRating();
				positiveDegree = (int)reviewList.stream().filter(x->x.getPredict() == 1).count();
				negativeDegree = (int)reviewList.stream().filter(x->x.getPredict() == 0).count();

			} else {

				avgRating = "별점정보 없음";
				positiveDegree = 0;
				negativeDegree = 0;
			}

			campEntity.setAvgRating(avgRating); // 네이버 별점
			campEntity.setReviewPositive(positiveDegree + ""); // 긍정 지수
			campEntity.setReviewNegative(negativeDegree + ""); // 부정 지수

		} // for

		return campList;
	} //

} //