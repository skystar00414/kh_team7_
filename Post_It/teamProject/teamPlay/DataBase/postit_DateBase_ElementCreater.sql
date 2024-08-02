-- Active: 1722306683503@@127.0.0.1@3306@postit
use postit;
# 추가해야할것 카테고리 ,권한 리스트, 상태 리스트, 사유리스트

# category : 게임, 운동, 축구, 올림픽, 뷰티, 음악, 쇼핑, 만화, 뉴스, 경제, 잡담
insert into category VALUES
(null, '게임'),(null, '운동'),(null, '축구'),
(null, '올림픽'),(null, '뷰티'),(null, '음악'),
(null, '쇼핑'),(null, '뉴스'),(null, '경제'),
(null, '잡담');

# authority : 유저, 게스트, 매니저, 운영자
insert into authority VALUES
('user', '유저'),('guest' ,'게스트'),('manager','매니저'),('admin','운영자');

# state : 활성화, 휴면상태, 일시정지, 영구정지
insert into statelist VALUES
('act', '활성화'),('sleep','수면'),('pause','일시정지'),('ban','영구정지');

# reason : 음란물 유포, 심각하거나 지속적인 규정 위반, 부정한 목적으로 다중계정 사용, 부적절한 계정명, 사칭 계정 사용, 악의적인 목적으로 생성한 계정
# 사이버 범죄 (마약, 국제 범죄, 사이버 테러, 총기 또는 도검(刀劍) 등의 위험물 밀수, 범죄 모의 등), 불법 프로그램 사용, 저작권 위반, IP 우회
insert into reasonlist VALUES
('음란물 유포'),
('심각하거나 지속적인 규정 위반, 부정한 목적으로 다중계정 사용'),
('부적절한 계정명'),
('사칭 계정 사용'),
('악의적인 목적으로 생성한 계정'),
('사이버 범죄 (마약, 국제 범죄, 사이버 테러, 총기 또는 도검(刀劍) 등의 위험물 밀수'),
('불법 프로그램 사용'),
('저작권 위반'),
('IP 우회');

insert into privacy(pr_id, pr_pw, pr_gender, pr_birth_day, pr_phone, pr_email, pr_au_key)
VALUES ("cocopam123", "cocopam123", "남", "1999-01-18", "010-2332-4771","cocopam123@naver.com", "user");

insert into privacy(pr_id, pr_pw, pr_gender, pr_birth_day, pr_phone, pr_email, pr_au_key)
VALUES ("qwer1234", "qwer1234", "여", "2000-12-30", "010-1111-2222","qwer1234@naver.com", "user");

select * from privacy;
select Ifnull(pr_id,0) from privacy WHERE pr_id = "cocopam123";
update privacy SET pr_failed = pr_failed + 1 WHERE pr_id = "cocopam123";



insert into border(bo_title) VALUES ("운동");

select * from border;

delete FROM border where bo_title = "운동";

select * FROM border WHERE bo_title = "축구";

select * FROM border where bo_title = "운동";

update border set
bo_oldtitle = bo_title,
bo_title = "독서",
bo_detail = "책을 읽어보자"
WHERE
bo_title = "운동";

-- 운동 border 에 축구 category 이런식으로 세분화 하는 부분입니다.
--  게임 border 에 엘든링, 서든어택, 등등의 카테고리를 가지게 할 수 있습니다.