--------------------------------------------------------
--  DDL for Table CAMPING_GROUNDS_TABLE
--------------------------------------------------------

  CREATE TABLE "CAMPING"."CAMPING_GROUNDS_TABLE" 
   (	"ID" NUMBER, 
	"IMAGE" VARCHAR2(500 BYTE), 
	"CAMP_NAME" VARCHAR2(100 BYTE), 
	"ADDRESS" VARCHAR2(200 BYTE), 
	"ENVIRONMENT" VARCHAR2(200 BYTE), 
	"CAMPING_TYPE" VARCHAR2(100 BYTE), 
	"OPERATING_PERIOD" VARCHAR2(100 BYTE), 
	"OPERATING_DAYS" VARCHAR2(100 BYTE), 
	"RESERVATION_METHOD" VARCHAR2(200 BYTE), 
	"NEARBY_FACILITIES" VARCHAR2(500 BYTE), 
	"INTRO" VARCHAR2(4000 BYTE), 
	"CAMP_ITEMS" VARCHAR2(1000 BYTE), 
	"FACILITY" VARCHAR2(200 BYTE), 
	"OTHER_INFO" VARCHAR2(200 BYTE), 
	"ADDITIONAL_FACILITY" VARCHAR2(200 BYTE), 
	"FLOOR_TYPE" VARCHAR2(200 BYTE), 
	"SITE_SIZE" VARCHAR2(200 BYTE), 
	"CAMPING_EQUIPMENT_RENTAL" VARCHAR2(200 BYTE), 
	"GLAMPING_FACILITIES" VARCHAR2(200 BYTE), 
	"CARAVAN_FACILITIES" VARCHAR2(200 BYTE), 
	"PET_ACCESS" VARCHAR2(200 BYTE), 
	"FIREPLACE" VARCHAR2(200 BYTE), 
	"SAFETY_FACILITIES" VARCHAR2(200 BYTE), 
	"CAMP_INTRO_IMAGES" VARCHAR2(4000 BYTE), 
	"BOX_PHOTO_IMAGES" LONG
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;

   COMMENT ON COLUMN "CAMPING"."CAMPING_GROUNDS_TABLE"."IMAGE" IS '메인사진 URL';
   COMMENT ON COLUMN "CAMPING"."CAMPING_GROUNDS_TABLE"."CAMP_NAME" IS '이름';
   COMMENT ON COLUMN "CAMPING"."CAMPING_GROUNDS_TABLE"."ADDRESS" IS '주소';
   COMMENT ON COLUMN "CAMPING"."CAMPING_GROUNDS_TABLE"."ENVIRONMENT" IS '캠핑장 환경';
   COMMENT ON COLUMN "CAMPING"."CAMPING_GROUNDS_TABLE"."CAMPING_TYPE" IS '캠핑장 유형';
   COMMENT ON COLUMN "CAMPING"."CAMPING_GROUNDS_TABLE"."OPERATING_PERIOD" IS '운영기간';
   COMMENT ON COLUMN "CAMPING"."CAMPING_GROUNDS_TABLE"."OPERATING_DAYS" IS '운영일';
   COMMENT ON COLUMN "CAMPING"."CAMPING_GROUNDS_TABLE"."RESERVATION_METHOD" IS '예약방법';
   COMMENT ON COLUMN "CAMPING"."CAMPING_GROUNDS_TABLE"."NEARBY_FACILITIES" IS '주변이용가능시설';
   COMMENT ON COLUMN "CAMPING"."CAMPING_GROUNDS_TABLE"."INTRO" IS '캠핑장 소개';
   COMMENT ON COLUMN "CAMPING"."CAMPING_GROUNDS_TABLE"."CAMP_ITEMS" IS '캠핑장 시설';
   COMMENT ON COLUMN "CAMPING"."CAMPING_GROUNDS_TABLE"."FACILITY" IS '주요시설';
   COMMENT ON COLUMN "CAMPING"."CAMPING_GROUNDS_TABLE"."OTHER_INFO" IS '기타 정보';
   COMMENT ON COLUMN "CAMPING"."CAMPING_GROUNDS_TABLE"."ADDITIONAL_FACILITY" IS '기타 부대시설';
   COMMENT ON COLUMN "CAMPING"."CAMPING_GROUNDS_TABLE"."FLOOR_TYPE" IS '바닥형태';
   COMMENT ON COLUMN "CAMPING"."CAMPING_GROUNDS_TABLE"."SITE_SIZE" IS '사이트 크기';
   COMMENT ON COLUMN "CAMPING"."CAMPING_GROUNDS_TABLE"."CAMPING_EQUIPMENT_RENTAL" IS '캠핑장비대여';
   COMMENT ON COLUMN "CAMPING"."CAMPING_GROUNDS_TABLE"."GLAMPING_FACILITIES" IS '글램핑 내부시설';
   COMMENT ON COLUMN "CAMPING"."CAMPING_GROUNDS_TABLE"."CARAVAN_FACILITIES" IS '카라반 내부시설';
   COMMENT ON COLUMN "CAMPING"."CAMPING_GROUNDS_TABLE"."PET_ACCESS" IS '반려동물 출입';
   COMMENT ON COLUMN "CAMPING"."CAMPING_GROUNDS_TABLE"."FIREPLACE" IS '화로대';
   COMMENT ON COLUMN "CAMPING"."CAMPING_GROUNDS_TABLE"."SAFETY_FACILITIES" IS '안전시설현황';
   COMMENT ON COLUMN "CAMPING"."CAMPING_GROUNDS_TABLE"."CAMP_INTRO_IMAGES" IS '캠핑장 소개 이미지';
   COMMENT ON COLUMN "CAMPING"."CAMPING_GROUNDS_TABLE"."BOX_PHOTO_IMAGES" IS '캠핑장 사진';
