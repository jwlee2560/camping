-- 캠핑장 정보 테이블
create table camp_info_tbl (
	id 						NUMBER(10),
	camp_name				VARCHAR2(100 char),
	cate1					VARCHAR2(100 char),
	cate2					VARCHAR2(100 char),
	cate3					VARCHAR2(100 char),
	sido_name				VARCHAR2(30 char),
	sigugun_name			VARCHAR2(30 char),
	eupmyundong_name		VARCHAR2(30 char),
	ry_name					VARCHAR2(30 char),
	bunji_name				VARCHAR2(30 char),
	road_name				VARCHAR2(30 char),
	building_num			VARCHAR2(30 char),
	latitude				NUMBER(8,4),
	longitude				NUMBER(8,4),
	zip						VARCHAR2(5 char),
	road_address			VARCHAR2(300 char),
	jibun_address			VARCHAR2(300 char),
	phone					VARCHAR2(20 char),
	homepage				VARCHAR2(300 char),
	vendor					VARCHAR2(100 char),
	weekday_op_status		VARCHAR2(20 char),
	weekend_op_status		VARCHAR2(20 char),
	spring_op_status		VARCHAR2(20 char),
	summer_op_status		VARCHAR2(20 char),
	fall_op_status			VARCHAR2(20 char),
	winter_op_status		VARCHAR2(20 char),
	facil_electricity		VARCHAR2(20 char),
	facil_hot_water			VARCHAR2(20 char),
	facil_wifi				VARCHAR2(20 char),
	facil_campfire			VARCHAR2(20 char),
	facil_trail				VARCHAR2(20 char),
	facil_pool				VARCHAR2(20 char),
	facil_playground		VARCHAR2(20 char),
	facil_mart				VARCHAR2(20 char),
	facil_restroom			VARCHAR2(4 char),
	facil_showerroom		VARCHAR2(4 char),
	facil_sink				VARCHAR2(4 char),
	facil_extinguisher		VARCHAR2(10 char),
	surr_facil_fishing		VARCHAR2(30 char),
	surr_facil_trail		VARCHAR2(30 char),
	surr_facil_beach		VARCHAR2(30 char),
	surr_facil_maritime_leisure	VARCHAR2(30 char),
	surr_facil_valley		VARCHAR2(30 char),
	surr_facil_stream		VARCHAR2(30 char),
	surr_facil_pool			VARCHAR2(30 char),
	surr_facil_youth_experience	VARCHAR2(30 char),
	surr_facil_rural_experience	VARCHAR2(30 char),
	surr_facil_childrens_play	VARCHAR2(30 char),
	glam_bed				VARCHAR2(30 char),
	glam_tv					VARCHAR2(30 char),
	glam_freezer			VARCHAR2(30 char),
	glam_internet			VARCHAR2(30 char),
	glam_restroom			VARCHAR2(30 char),
	glam_aircon				VARCHAR2(30 char),
	glam_heater				VARCHAR2(30 char),
	glam_cookware			VARCHAR2(30 char),
	facil_characteristics	VARCHAR2(1000 char),
	facil_detail			LONG,
	reg_date				CHAR(10)
);

ALTER TABLE camp_info_tbl
ADD CONSTRAINT camp_info_tbl_pk PRIMARY KEY(id);
COMMENT ON COLUMN camp_info_tbl.id	is '아이디';
COMMENT ON COLUMN camp_info_tbl.camp_name	is '시설명';
COMMENT ON COLUMN camp_info_tbl.cate1	is '카테고리1';
COMMENT ON COLUMN camp_info_tbl.cate2	is '카테고리2';
COMMENT ON COLUMN camp_info_tbl.cate3	is '카테고리3';
COMMENT ON COLUMN camp_info_tbl.sido_name	is '시도 명칭';
COMMENT ON COLUMN camp_info_tbl.sigugun_name	is '시군구 명칭';
COMMENT ON COLUMN camp_info_tbl.eupmyundong_name	is '법정읍면동명칭';
COMMENT ON COLUMN camp_info_tbl.ry_name	is '리 명칭';
COMMENT ON COLUMN camp_info_tbl.bunji_name	is '번지';
COMMENT ON COLUMN camp_info_tbl.road_name	is '도로명 이름';
COMMENT ON COLUMN camp_info_tbl.building_num	is '건물 번호';
COMMENT ON COLUMN camp_info_tbl.latitude	is '위도';
COMMENT ON COLUMN camp_info_tbl.longitude	is '경도';
COMMENT ON COLUMN camp_info_tbl.zip	is '우편번호';
COMMENT ON COLUMN camp_info_tbl.road_address	is '도로명주소';
COMMENT ON COLUMN camp_info_tbl.jibun_address	is '지번주소';
COMMENT ON COLUMN camp_info_tbl.phone	is '전화번호';
COMMENT ON COLUMN camp_info_tbl.homepage	is '홈페이지';
COMMENT ON COLUMN camp_info_tbl.vendor	is '사업주체';
COMMENT ON COLUMN camp_info_tbl.weekday_op_status	is '평일 운영 여부';
COMMENT ON COLUMN camp_info_tbl.weekend_op_status	is '주말 운영 여부';
COMMENT ON COLUMN camp_info_tbl.spring_op_status	is '봄 운영 여부';
COMMENT ON COLUMN camp_info_tbl.summer_op_status	is '여름 운영 여부';
COMMENT ON COLUMN camp_info_tbl.fall_op_status	is '가을 운영 여부';
COMMENT ON COLUMN camp_info_tbl.winter_op_status	is '겨울 운영 여부';
COMMENT ON COLUMN camp_info_tbl.facil_electricity	is '부대시설 전기';
COMMENT ON COLUMN camp_info_tbl.facil_hot_water	is '부대시설 온수';
COMMENT ON COLUMN camp_info_tbl.facil_wifi	is '부대시설 무선인터넷';
COMMENT ON COLUMN camp_info_tbl.facil_campfire	is '부대시설 장작판매';
COMMENT ON COLUMN camp_info_tbl.facil_trail	is '부대시설 산책로';
COMMENT ON COLUMN camp_info_tbl.facil_pool	is '부대시설 물놀이장';
COMMENT ON COLUMN camp_info_tbl.facil_playground	is '부대시설 놀이터';
COMMENT ON COLUMN camp_info_tbl.facil_mart	is '부대시설 마트';
COMMENT ON COLUMN camp_info_tbl.facil_restroom	is '부대시설 화장실 수';
COMMENT ON COLUMN camp_info_tbl.facil_showerroom	is '부대시설 샤워실 수';
COMMENT ON COLUMN camp_info_tbl.facil_sink	is '부대시설 씽크대 수';
COMMENT ON COLUMN camp_info_tbl.facil_extinguisher	is '부대시설 소화기 수';
COMMENT ON COLUMN camp_info_tbl.surr_facil_fishing	is '주변 시설 낚시';
COMMENT ON COLUMN camp_info_tbl.surr_facil_trail	is '주변 시설 산책로';
COMMENT ON COLUMN camp_info_tbl.surr_facil_beach	is '주변 시설 물놀이(해수욕)';
COMMENT ON COLUMN camp_info_tbl.surr_facil_maritime_leisure	is '주변 시설 물놀이(수상레저)';
COMMENT ON COLUMN camp_info_tbl.surr_facil_valley	is '주변 시설 물놀이(계곡)';
COMMENT ON COLUMN camp_info_tbl.surr_facil_stream	is '주변 시설 물놀이(강)';
COMMENT ON COLUMN camp_info_tbl.surr_facil_pool	is '주변 시설 물놀이(수영장)';
COMMENT ON COLUMN camp_info_tbl.surr_facil_youth_experience	is '주변 시설 청소년체험시설';
COMMENT ON COLUMN camp_info_tbl.surr_facil_rural_experience	is '주변 시설 농어촌체험시설';
COMMENT ON COLUMN camp_info_tbl.surr_facil_childrens_play	is '주변 시설 어린이놀이시설';
COMMENT ON COLUMN camp_info_tbl.glam_bed	is '글램핑 침대';
COMMENT ON COLUMN camp_info_tbl.glam_tv	is '글램핑 티비';
COMMENT ON COLUMN camp_info_tbl.glam_freezer	is '글램핑 냉장고';
COMMENT ON COLUMN camp_info_tbl.glam_internet	is '글램핑 유무선인터넷';
COMMENT ON COLUMN camp_info_tbl.glam_restroom	is '글램핑 내부화장실';
COMMENT ON COLUMN camp_info_tbl.glam_aircon	is '글램핑 에어컨';
COMMENT ON COLUMN camp_info_tbl.glam_heater	is '글램핑 난방기구';
COMMENT ON COLUMN camp_info_tbl.glam_cookware	is '글램핑 취사도구';
COMMENT ON COLUMN camp_info_tbl.facil_characteristics	is '시설 특징';
COMMENT ON COLUMN camp_info_tbl.facil_detail	is '시설 소개';
COMMENT ON COLUMN camp_info_tbl.reg_date	is '최종작성일';

CREATE SEQUENCE camp_info_tbl_seq
MINVALUE 1
MAXVALUE 9999999999
INCREMENT BY 1
START WITH 1
NOCYCLE;