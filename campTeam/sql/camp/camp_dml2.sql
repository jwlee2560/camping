-- 목적지가 경기도, 서울, 인천이면서, 산책을 원하는 경우
select sido_name, camp_name, longitude, latitude from camp_info_tbl
where sido_name in ('경기도', '인천광역시', '서울특별시')
and surr_facil_trail = '시설 주변 산책로 있음';

select count(*) from camp_info_tbl
where sido_name in ('경기도', '인천광역시', '서울특별시')
and surr_facil_trail = '시설 주변 산책로 있음'; -- 262

-- 목적지가 경기도 북부 이면서, 물놀이(수상레저) 를 원하는 경우
-- https://ko.wikipedia.org/wiki/%EA%B2%BD%EA%B8%B0%EB%B6%81%EB%B6%80
-- 경기 북부 : '연천군', '동두천시', '포천시', '파주시', '양주시', '의정부시', '구리시', '남양주시', '고양시', '가평군'
select sigugun_name, camp_name, longitude, latitude from camp_info_tbl
where sido_name = '경기도'
and sigugun_name in ('연천군', '동두천시', '포천시', '파주시', '양주시', '의정부시', '구리시', '남양주시', '고양시', '가평군')
and surr_facil_maritime_leisure = '시설 주변 물놀이(수상레저) 있음';

select count(*) from camp_info_tbl
where sido_name = '경기도'
and sigugun_name in ('연천군', '동두천시', '포천시', '파주시', '양주시', '의정부시', '구리시', '남양주시', '고양시', '가평군')
and surr_facil_maritime_leisure = '시설 주변 물놀이(수상레저) 있음';


-- 목적지가 경기 남부 이면서, 낚시를 원하는 경우
-- https://ko.wikipedia.org/wiki/%EA%B2%BD%EA%B8%B0%EB%82%A8%EB%B6%80
-- 경기 남부 : '안산시', '수원시', '용인시', '화성시', '성남시', '부천시', '평택시','안양시','시흥시','김포시','광주시','하남시',
-- '광명시','군포시','오산시','이천시','안성시','의왕시','양평군','여주시', '과천시'
select camp_name, longitude, latitude from camp_info_tbl
where sido_name = '경기도'
and sigugun_name in ('안산시', '수원시', '용인시', '화성시', '성남시', '부천시', '평택시','안양시','시흥시','김포시','광주시','하남시',
'광명시','군포시','오산시','이천시','안성시','의왕시','양평군','여주시', '과천시')
and surr_facil_fishing = '낚시 시설 있음';

select count(*) from camp_info_tbl
where sido_name = '경기도'
and sigugun_name in ('안산시', '수원시', '용인시', '화성시', '성남시', '부천시', '평택시','안양시','시흥시','김포시','광주시','하남시',
'광명시','군포시','오산시','이천시','안성시','의왕시','양평군','여주시', '과천시')
and surr_facil_fishing = '낚시 시설 있음';


select * from camp_info_tbl
where sido_name = '경기도'
and surr_facil_trail = '시설 주변 산책로 없음';

--------------------------------------------------------------------------------------

-- 선택사항 : 경기/서울/인천 전체 + 테마(산책로) + 평택(검색어)
select sido_name, sigugun_name, camp_name, surr_facil_trail, longitude, latitude from camp_info_tbl
where sido_name in ('경기도', '인천광역시', '서울특별시')
and surr_facil_trail = '시설 주변 산책로 있음'
and (road_address like '%평택%' or jibun_address like '%평택%');

-- 선택사항 : 경기 남부 + 테마(수상레저) + 평택(검색어) : 결과는 없음 !
select sigugun_name, camp_name, surr_facil_maritime_leisure, longitude, latitude from camp_info_tbl
where sigugun_name in ('안산시', '수원시', '용인시', '화성시', '성남시', '부천시', '평택시','안양시','시흥시','김포시','광주시','하남시', '광명시','군포시','오산시','이천시','안성시','의왕시','양평군','여주시', '과천시')
and surr_facil_maritime_leisure = '시설 주변 물놀이(수상레저) 있음'
and (road_address like '%평택%' or jibun_address like '%평택%');

-- 선택사항 : 경기 남부 + 테마(낚시) + 평택(검색어) : 결과 없음
select camp_name, longitude, latitude from camp_info_tbl
where sigugun_name in ('안산시', '수원시', '용인시', '화성시', '성남시', '부천시', '평택시','안양시','시흥시','김포시','광주시','하남시',
'광명시','군포시','오산시','이천시','안성시','의왕시','양평군','여주시', '과천시')
and surr_facil_fishing = '낚시 시설 있음'
and (road_address like '%평택%' or jibun_address like '%평택%');

-- 선택사항 : 경기 남부 + 테마(산책) + 평택(검색어) : 결과 2건
select sigugun_name, camp_name, surr_facil_trail, longitude, latitude from camp_info_tbl
where sigugun_name in ('안산시', '수원시', '용인시', '화성시', '성남시', '부천시', '평택시','안양시','시흥시','김포시','광주시','하남시','광명시','군포시','오산시','이천시','안성시','의왕시','양평군','여주시', '과천시')
and surr_facil_trail = '시설 주변 산책로 있음'
and (road_address like '%평택%' or jibun_address like '%평택%');

-- 선택사항 : 경기 북부 + 테마(바다-해수욕장) + 가평(검색어) : 결과 1건
select sigugun_name, surr_facil_beach, camp_name, longitude, latitude from camp_info_tbl
where sigugun_name in ('연천군', '동두천시', '포천시', '파주시', '양주시', '의정부시', '구리시', '남양주시', '고양시', '가평군')
and surr_facil_beach = '시설 주변 물놀이(해수욕장) 있음'
and (road_address like '%가평%' or jibun_address like '%가평%');


select sido_name, sigugun_name, camp_name, longitude, latitude from camp_info_tbl
where (road_address like '%평택%' or jibun_address like '%평택%');
