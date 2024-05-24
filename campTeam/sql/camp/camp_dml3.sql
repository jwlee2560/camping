select cit.CAMP_NAME,cit.LATITUDE,cit.LONGITUDE,cit.ROAD_ADDRESS ,cit.JIBUN_ADDRESS, cit.FACIL_DETAIL, cgt.IMAGE
from camp_info_tbl cit, CAMPING_GROUNDS_TABLE cgt
where cit.CAMP_NAME = cgt.CAMP_NAME 
AND cit.SIDO_NAME = '경기도'
and cit.sigugun_name in ('연천군', '동두천시', '포천시', '파주시', '양주시', '의정부시', '구리시', '남양주시', '고양시', '가평군')
and cit.surr_facil_maritime_leisure = '시설 주변 물놀이(수상레저) 있음'
and (cit.road_address like '%가평%' or cit.jibun_address like '%가평%');



--캠핑장명 cgt.camp_name공백('',\t,\n 등등) 일괄제거
update CAMPING_GROUNDS_TABLE set camp_name = regexp_replace(camp_name, '\s', '');

SELECT id,LENGTH(camp_name),LENGTH(trim(CAMP_NAME))  FROM CAMPING_GROUNDS_TABLE cgt WHERE id = 1;

SELECT LENGTH(trim('새연 글램핑 카라반 설악2호점')) FROM dual;

SELECT id,image FROM CAMPING_GROUNDS_TABLE cgt WHERE CAMP_NAME = '가평무릉도원캠핑장';
UPDATE CAMPING_GROUNDS_TABLE SET IMAGE = NULL WHERE id = 3;


