CREATE TABLE camping_grounds_table (
    ID NUMBER,
    img_url VARCHAR2(500),
    name VARCHAR2(100),
    address VARCHAR2(200),
    environment VARCHAR2(200),
    camping_type VARCHAR2(100),
    operating_period VARCHAR2(100),
    operating_days VARCHAR2(100),
    reservation_method VARCHAR2(200),
    nearby_facilities VARCHAR2(500),
    intro VARCHAR2(4000),
    camp_items VARCHAR2(1000),
    facility VARCHAR2(200),
    other_info VARCHAR2(200),
    additional_facility VARCHAR2(200),
    floor_type VARCHAR2(200),
    site_size VARCHAR2(200),
    camping_equipment_rental VARCHAR2(200),
    glamping_facilities VARCHAR2(200),
    caravan_facilities VARCHAR2(200),
    pet_access VARCHAR2(200),
    fireplace  VARCHAR2(200),
    safety_facilities VARCHAR2(200),
    camp_intro_images VARCHAR2(4000),
    box_photo_images LONG
);

COMMENT ON COLUMN camping_grounds_table.img_url IS '메인사진 URL';
COMMENT ON COLUMN camping_grounds_table.name IS '이름';
COMMENT ON COLUMN camping_grounds_table.address IS '주소';
COMMENT ON COLUMN camping_grounds_table.environment IS '캠핑장 환경';
COMMENT ON COLUMN camping_grounds_table.camping_type IS '캠핑장 유형';
COMMENT ON COLUMN camping_grounds_table.operating_period IS '운영기간';
COMMENT ON COLUMN camping_grounds_table.operating_days IS '운영일';
COMMENT ON COLUMN camping_grounds_table.reservation_method IS '예약방법';
COMMENT ON COLUMN camping_grounds_table.nearby_facilities IS '주변이용가능시설';
COMMENT ON COLUMN camping_grounds_table.intro IS '캠핑장 소개';
COMMENT ON COLUMN camping_grounds_table.camp_items IS '캠핑장 시설';
COMMENT ON COLUMN camping_grounds_table.facility IS '주요시설';
COMMENT ON COLUMN camping_grounds_table.other_info IS '기타 정보';
COMMENT ON COLUMN camping_grounds_table.additional_facility IS '기타 부대시설';
COMMENT ON COLUMN camping_grounds_table.floor_type IS '바닥형태';
COMMENT ON COLUMN camping_grounds_table.site_size IS '사이트 크기';
COMMENT ON COLUMN camping_grounds_table.camping_equipment_rental IS '캠핑장비대여';
COMMENT ON COLUMN camping_grounds_table.glamping_facilities IS '글램핑 내부시설';
COMMENT ON COLUMN camping_grounds_table.caravan_facilities IS '카라반 내부시설';
COMMENT ON COLUMN camping_grounds_table.pet_access IS '반려동물 출입';
COMMENT ON COLUMN camping_grounds_table.fireplace IS '화로대';
COMMENT ON COLUMN camping_grounds_table.safety_facilities IS '안전시설현황';
COMMENT ON COLUMN camping_grounds_table.camp_intro_images IS '캠핑장 소개 이미지';
COMMENT ON COLUMN camping_grounds_table.box_photo_images IS '캠핑장 사진';

CREATE SEQUENCE camping_seq
START WITH 1
INCREMENT BY 1
MAXVALUE 99999
NOCYCLE;