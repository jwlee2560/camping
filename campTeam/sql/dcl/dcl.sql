-- 프로젝트 전용 계정 생성
-- cmd -> sqlpuls "/as sysdba"

create user camping identified by camping;

grant connect, resource, create view to camping;

alter user camping account unlock;

alter user camping identified by camping;