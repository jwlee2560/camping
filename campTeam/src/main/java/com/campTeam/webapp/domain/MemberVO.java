package com.campTeam.webapp.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name="member_tbl")
@Slf4j
//@Getter
//@Setter
// @Data
public class MemberVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 회원 아이디 */
	@Id
	@Column // (name="id")
	private String id;
	
	/** 회원 패쓰워드 */
	@Column
	private String password;
	
	/** 회원 이름 */
	@Column(name="name", columnDefinition = "NVARCHAR2(100)")
	private String name;
	
	/** 회원 성별 */
	@Column
	private String gender;
	
	/** 회원 나이(연령) */
	@Column
	private int age;
	
	/** 회원 이메일 */
	@Column(name="email", columnDefinition = "NVARCHAR2(50)")
	private String email;
	
	/** 회원 연락처(휴대폰) */
	@Column(name="mobile", columnDefinition = "NVARCHAR2(13)")
	private String mobile;
	
	/** 회원 연락처(지역전화) */
	@Column(name="phone", columnDefinition = "NVARCHAR2(13)")
	private String phone;
	
	/** 회원 우편번호 */
	@Column
	private String zip;
	
	/** 회원 도로명 주소 */
	@Column(name="road_address", columnDefinition = "NVARCHAR2(50)")
	private String roadAddress;
	
	/** 회원 지번 주소 */
	@Column(name="jibun_address", columnDefinition = "NVARCHAR2(50)")
	private String jibunAddress;
	
	/** 회원 상세 주소 */
	@Column(name="detail_address", columnDefinition = "NVARCHAR(50)")
	private String detailAddress;
	
	/** 회원 생일 */
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;
	
	/** 회원 가입일 */
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date joindate;
	
	/** 회원 활성화 여부 */
	@Column
	private int enabled;
	
	/*
	 * @Column private String role;
	 */

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getRoadAddress() {
		return roadAddress;
	}

	public void setRoadAddress(String roadAddress) {
		this.roadAddress = roadAddress;
	}

	public String getJibunAddress() {
		return jibunAddress;
	}

	public void setJibunAddress(String jibunAddress) {
		this.jibunAddress = jibunAddress;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getJoindate() {
		return joindate;
	}

	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

//	public String getRole() {
//		return role;
//	}
//
//	public void setRole(String role) {
//		this.role = role;
//	}

	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", password=" + password + ", name=" + name + ", gender=" + gender + ", age="
				+ age + ", email=" + email + ", mobile=" + mobile + ", phone=" + phone + ", zip=" + zip
				+ ", roadAddress=" + roadAddress + ", jibunAddress=" + jibunAddress + ", detailAddress=" + detailAddress
				+ ", birthday=" + birthday + ", joindate=" + joindate + ", enabled=" + enabled + "]";
	}

}