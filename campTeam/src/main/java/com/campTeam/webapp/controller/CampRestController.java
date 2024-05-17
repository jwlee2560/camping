package com.campTeam.webapp.controller;

import org.apache.commons.codec.Charsets;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CampRestController {

	@GetMapping("/predictCamp")
	public ResponseEntity<String> predictCamp(@RequestParam("region") String region,
			@RequestParam("thema") String thema,
			@RequestParam("searchWord") String searchWord){
		
		log.info("지역:{}",region);
		log.info("테마:{}",thema);
		log.info("검색어:{}",searchWord);
		
	    // 지역
        // 경기 북부 : 연천군 포천시 가평군 양주시 남양주시 고양시 구리시 의정부시 김포시 동두천시
		// 경기 남부 : 양평군 여주시 이천시 광주시 안성시 하남시 과천시 군포시 의왕시 안산시 광명시 시흥시 오산시
		//           부천시 성남시 용인시 수원시 화성시 평택시
		
		
		// https://developers.kakao.com/docs/latest/ko/local/dev-guide
		// curl -v -G GET "https://dapi.kakao.com/v2/local/search/address.json" 
		// -H "Authorization: KakaoAK 480a4ff426463f83906e67a2cfb10aeb" --data-urlencode "query=전북 삼성동 100" 

		String KAKAO_APIKEY ="KakaoAK 480a4ff426463f83906e67a2cfb10aeb";
		String reqUrl = "https://dapi.kakao.com/v2/local/search/address.json"; // basic URL
		
		// 타임아웃 설정 : HttpComponentsClientHttpRequestFactory 객체 생성
        HttpComponentsClientHttpRequestFactory factory 
        	= new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(5000); // 타임아웃  5초

        // URL 같은 기본적인 자바의 클래스를 활용할 수도 있지만  Apache HttpComponents API를 활용할 수도 있습니다.
        // https://hc.apache.org/httpcomponents-client-5.3.x/index.html
        // Apache HttpComponents : 각 호스트(IP와 Port의 조합)당 커넥션 풀에 생성가능한 커넥션 수
        HttpClient httpClient = HttpClientBuilder.create().build();
        factory.setHttpClient(httpClient);
		
        // 실질적인 proxy 컨트롤러의 핵심은 RestTemplate입니다.
        // 웹 브라우저에서 노출 위험이 있는 정보(가령, 개인 REST key 등)을 백단에서 처리하기 때문에 
        // 보다 안전하게 REST Service를 구현할 수 있습니다.
        // RestTemplate
		RestTemplate restTemplate = new RestTemplate(factory);

		// HTTP header 설정
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		headers.add("Authorization", KAKAO_APIKEY);
		
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        
        // REST Service URL 설정 : 기본 API 주소 및 인자 설정
		String url = UriComponentsBuilder.fromHttpUrl(reqUrl)
					.queryParam("query", "도로명주소")
					.build()
					.encode(Charsets.toCharset("UTF-8")) // 인코딩
					.toUriString();
		
		// 응답(response) 정보
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class); 
		
		log.info("response : " + response);
		log.info("response Code : " + response.getStatusCode());
		log.info("response Body : " + response.getBody());
		
		// 추후에 할 수 있다면 try ~ catch 구문을 두어서 HTTP error 들에 대비된 코드를 삽입하여 보완하는 것이 좋습니다.
		// return new ResponseEntity<String>(response, headers, HttpStatus.OK);
		return response;
	}
}
