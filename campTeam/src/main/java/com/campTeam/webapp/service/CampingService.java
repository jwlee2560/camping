package com.campTeam.webapp.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.campTeam.webapp.dao.CampRepository;
import com.campTeam.webapp.domain.CampEntity;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CampingService {
	
	@Autowired
	CampRepository campRepo;

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

}
