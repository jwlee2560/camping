-- 전체 캠핑장 평가수
select count(*) from camp_review_tbl2 where predict in (0,1);

-- 특정 캠핑장 전체 평가 수
select count(*) from camp_review_tbl2
where camp_name = '횡성동당미계곡캠핑장';

-- 특정 캠핑장 부정평 비율
select count(*) from camp_review_tbl2
where camp_name = '횡성동당미계곡캠핑장'
and predict = 0;

select round((select count(*) from camp_review_tbl2
              where camp_name = '횡성동당미계곡캠핑장'
              and predict = 0) /
             (select count(*) from camp_review_tbl2
              where camp_name = '횡성동당미계곡캠핑장') * 100, 2) || ' %' as "부정비율"
from dual;

-- 특정 캠핑장 긍정평 비율
select count(*) from camp_review_tbl2
where camp_name = '횡성동당미계곡캠핑장'
and predict = 1;

select round((select count(*) from camp_review_tbl2
              where camp_name = '횡성동당미계곡캠핑장'
              and predict = 1) /
             (select count(*) from camp_review_tbl2
              where camp_name = '횡성동당미계곡캠핑장') * 100, 2) || ' %' as "긍정비율"
from dual;

-- 특정 캠핑장 네이버 평점
select distinct avg_rating from camp_review_tbl2
where camp_name = '횡성동당미계곡캠핑장';

select distinct avg_rating from camp_review_tbl2
where camp_name = '휴가든 카라반';

-- 특정 위도/경도 내의 캠핑장
select * from camp_info_tbl
where latitude >= 37.66078198940813 and latitude <= 37.84064631059187
and longitude >= 127.36343434453651 and  longitude <= 127.5909143354635;