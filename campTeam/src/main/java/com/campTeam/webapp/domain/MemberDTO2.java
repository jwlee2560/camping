package com.campTeam.webapp.domain;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Entity
@Slf4j
public class MemberDTO2 {
	
	@Id
	@Column
	/** 회원 아이디 */
	private String id;
	
	@Column
	/** 회원 패쓰워드 */
	private String password;
	
	@Column
	/** 회원 이름 */
	private String name;
	
	@Column
	/** 회원 성별 */
	private String gender;
	
	@Column
	/** 회원 나이(연령) */
	private int age;
	
	@Column
	/** 회원 이메일 */
	private String email;
	
	@Column
	/** 회원 연락처(휴대폰) */
	private String mobile;
	
	@Column
	/** 회원 연락처(지역전화) */
	private String phone;
	
	@Column
	/** 회원 우편번호 */
	private String zip;
	
	@Column(name="road_Address")
	/** 회원 도로명 주소 */
	private String roadAddress;
	
	@Column(name="jibun_Address")
	/** 회원 지번 주소 */
	private String jibunAddress;
	
	@Column(name="detail_Address")
	/** 회원 상세 주소 */
	private String detailAddress;
	
	@Column
	/** 회원 생일 */
//	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;
	
	@Column
	/** 회원 가입일 */
//	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date joindate;
	
	@Column
	/** 회원 활성화 여부 */
	private int enabled;
	
	@Column
	private String role;
	
	
}	
