package com.campTeam.webapp.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openkoreantext.processor.OpenKoreanTextProcessorJava;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.campTeam.webapp.domain.CampEntity;
import com.campTeam.webapp.domain.CampReviewVO;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CampingCrawlService {

	/**
	 * 캠핑 정보 CSV에서 캠핑업체명 목록 추출
	 * @return
	 */
	public List<String> readCampingsFromCSV() {

		List<String> campings = new ArrayList<>();
		String filename = "한국문화정보원_전국 문화 여가 활동 시설(캠핑) 데이터_20221130.CSV";

		String[] campInfo;
		CampEntity campEntity = null;

		try {

			ClassPathResource resource = new ClassPathResource("/csv/" + filename);

			CSVReader csvReader
				= new CSVReader(new InputStreamReader(new FileInputStream(resource.getFile()), "EUC-KR"));

			csvReader.readNext(); // 컬럼명 제외

			while ((campInfo = csvReader.readNext()) != null) {

				campEntity = new CampEntity();

				campings.add(campInfo[0]); // 캠핑장 이름만 획득
			} // while

		} catch (IOException e) {
			log.error("readCampingsFromCSV : {}", e);
			e.printStackTrace();
		} catch (CsvValidationException e) {
			log.error("readCampingsFromCSV : {}", e);
			e.printStackTrace();
		}

		return campings;
	}

	/**
	 * 네이버(Naver) 캠핑 별점 및 리뷰 획득
	 *
	 * @param campName 업체명 ex) 별빛누리글램핑
	 * @return 캠핑 업체 별점 리뷰 현황
	 */
	public CampReviewVO crawlCampReviews(String campName) {

		CampReviewVO campReviewVO = new CampReviewVO();

		campName = campName.replaceAll("[(주) | (유)]", "");

		String url = "https://pcmap.place.naver.com/accommodation/list?query=" + campName;

		Document doc = null;

		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			log.error("crawl error : {}", e);
			e.printStackTrace();
		}

		Elements el = doc.select("body[class='place_on_pcmap'] script");

		// 이 부분에 언급된 캠핑 장소에 대한 naver 고유 id (ex. 1156908119)가 존재함
		// ex) ... "AccommodationSummary:1156908119:

		String scriptContent = el.get(2).html();

		int idx = scriptContent.indexOf("AccommodationSummary:");
		String campingNaverId = "";

		try {

			campingNaverId = scriptContent.substring(idx).split(":")[1]; // 별점없는 경우?? (0점)

		} catch (Exception e) { // 별점있는 경우 ??
			idx = scriptContent.indexOf("PlaceSummary:");
			campingNaverId = scriptContent.substring(idx + "PlaceSummary:".length()).split("\"")[0]; // 별점있는 경우??
		}

		log.info("campingNaverId : " + campingNaverId);

		////////////////////////////////////////////////////////////////////////////////////

		// 캠핑장 리뷰 주소 : https://pcmap.place.naver.com/accommodation/1156908119/review/visitor?businessCategory=camping
		String reviewUrl = "https://pcmap.place.naver.com/accommodation/" + campingNaverId
						 + "/review/visitor?businessCategory=camping";

		Document docReview = null;

		try {
			docReview = Jsoup.connect(reviewUrl).get();
		} catch (IOException e) {
			log.error("crawl error : {}", e);
			e.printStackTrace();
		}

		String reviewContent = docReview.select("div[class='place_section_content']").text();

		log.info("reviewContent : {}", reviewContent);

		/////////////////////////////////////////////////////////////////////////////////

		// 별점
		// ex) ★4.74   3,212개 평점 (3,082명 참여)안내 ....
		// ex) <span class="PXMot LXIwF">  ....  <em>4.74<em>

		String avgRating = "";

		try {
			Element avgRatingBefore = docReview.select("span[class='PXMot LXIwF']").get(0);
			avgRating = avgRatingBefore.text().replace("별점", "");
		} catch (Exception e) {
			avgRating = "0";
		}

		log.info("별점 : " + avgRating);

		campReviewVO.setCampName(campName);
		campReviewVO.setAvgRating(avgRating);
		campReviewVO.setReviews(reviewContent);

		return campReviewVO;
	} //

	/**
	 * 네이버(Naver) 캠핑 별점 및 리뷰 획득
	 *
	 * @param campName 업체명 ex) 별빛누리글램핑
	 * @return 캠핑 업체 별점 리뷰 현황
	 */
	public List<CampReviewVO> crawlCampReviewsDetail(String campName) {

		List<CampReviewVO> list = new ArrayList<>();
		CampReviewVO campReviewVO = null;

		String url = "https://pcmap.place.naver.com/accommodation/list?query=" + campName;

		Document doc = null;

		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			log.error("crawl error : {}", e);
			e.printStackTrace();
		}

		Elements el = doc.select("body[class='place_on_pcmap'] script");

		// 이 부분에 언급된 캠핑 장소에 대한 naver 고유 id (ex. 1156908119)가 존재함
		// ex) ... "AccommodationSummary:1156908119:

		String scriptContent = el.get(2).html();

		int idx = scriptContent.indexOf("AccommodationSummary:");
		String campingNaverId = "";

		try {

			campingNaverId = scriptContent.substring(idx).split(":")[1]; // 별점없는 경우?? (0점)

		} catch (Exception e) { // 별점있는 경우 ??
			idx = scriptContent.indexOf("PlaceSummary:");
			campingNaverId = scriptContent.substring(idx + "PlaceSummary:".length()).split("\"")[0]; // 별점있는 경우??
		}

		log.info("campingNaverId : " + campingNaverId);

		////////////////////////////////////////////////////////////////////////////////////

		// 캠핑장 리뷰 주소 : https://pcmap.place.naver.com/accommodation/1156908119/review/visitor?businessCategory=camping
		String reviewUrl = "https://pcmap.place.naver.com/accommodation/" + campingNaverId
						 + "/review/visitor?businessCategory=camping";

		Document docReview = null;

		try {
			docReview = Jsoup.connect(reviewUrl).get();
		} catch (IOException e) {
			log.error("crawl error : {}", e);
			e.printStackTrace();
		}

		/////////////////////////////////////////////////////////////////////////////////

		// 별점
		// ex) ★4.74   3,212개 평점 (3,082명 참여)안내 ....
		// ex) <span class="PXMot LXIwF">  ....  <em>4.74<em>

		String avgRating = "";

		try {
			Element avgRatingBefore = docReview.select("span[class='PXMot LXIwF']").get(0);
			avgRating = avgRatingBefore.text().replace("별점", "");
		} catch (Exception e) {
			avgRating = "0";
		}

		log.info("별점 : " + avgRating);

		String reviewContent = docReview.select("div[class='place_section_content']").text();

		log.info("reviewContent : {}", reviewContent);

		// 불용어 제거 => 미제거시 긍정/부정 판정율 60%대로 떨어짐
		// "인원", 숫자 성분, "이 키워드를 선택한 인원", 사진, 날짜, 특수기호

		// 형태소 분석 엔진으로 정제
		CharSequence normalized = OpenKoreanTextProcessorJava.normalize(reviewContent);
		String[] tokens = normalized.toString().split("\\.");

		// 불용어 제거
		// 참여, 인원, 년, 월, 일
		for (String token : tokens) {

			reviewContent = token.replaceAll("[ 년 | 월 | 일 ]", " ")
								 .replaceAll("[^가-힣]", " ")
								 .replaceAll("\\s+", " ");  // 한글 이외 글자 및 불용어, 중복 띄워쓰기 배제
			log.info("reviewContent : " + reviewContent);

			campReviewVO = new CampReviewVO();
			campReviewVO.setCampName(campName);
			campReviewVO.setAvgRating(avgRating);
			campReviewVO.setReviews(reviewContent == null ? "" : reviewContent);

			if (reviewContent.trim().equals("") == false) { // 공백아닐 경우만 저장
				list.add(campReviewVO);
			}
		}

		return list;
	} //

} //