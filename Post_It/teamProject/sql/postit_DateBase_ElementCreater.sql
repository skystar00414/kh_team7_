-- Active: 1722306683503@@127.0.0.1@3306@postit
use postit;
select * from border;

# border : 대단원에 해당함 (ex -> 운동)
insert into border(bo_title) VALUES
("운동"),("게임"),("여행"),("컴퓨터"),("올림픽"),("음악"),("경제");

select * from category;

# category : 소단원에 해당함 (ex -> 운동 中 축구)
insert into category(ca_bo_number, ca_name) VALUES
(1,"야구"),(1,"축구"),(1,"테니스"),(1,"양궁");
-- 운동 tag

insert into category(ca_bo_number, ca_name) VALUES
(2,"바이오하자드"),(2,"테트리스"),(2,"메이플스토리"),(2,"블랙잭"),(2,"바카라");
-- 게임 tag

insert into category(ca_bo_number, ca_name) VALUES
(3,"일본"),(3,"중국"),(3,"미국"),(3,"태국"),(3,"영국");
-- 여행 tag

insert into category(ca_bo_number, ca_name) VALUES
(4,"Rust"),(4,"Java"),(4,"C"),(4,"C++"),(4,"JavaScript");
-- 컴퓨터 tag


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

insert into privacy(pr_id, pr_pw, pr_gender, pr_birth_day, pr_phone, pr_email, pr_au_key)
VALUES ("admin1234", "admin1234", "남", "2000-10-10", "010-1234-1234","admin1234@naver.com", "admin");

select * from privacy;
select Ifnull(pr_id,0) from privacy WHERE pr_id = "cocopam123";
update privacy SET pr_failed = pr_failed + 1 WHERE pr_id = "cocopam123";



select ca_number, ca_bo_number, ca_name;

select * from category;
select ca_number, ca_bo_number, ca_name from category
join border on bo_number = ca_bo_number
where bo_title = "게임";

select * from poster;
select poster.* from poster
join category on po_ca_number = ca_number
where ca_name = "야구";

-- 운동 border 에 축구 category 이런식으로 세분화 하는 부분입니다.
--  게임 border 에 엘든링, 서든어택, 등등의 카테고리를 가지게 할 수 있습니다.


insert into poster(po_bo_number, po_ca_number, po_pr_number,po_date, po_title, po_content)
VALUES(1,1,1,now(),"첫글","첫글작성을 해 보았는데용?");

insert into poster(po_bo_number, po_ca_number, po_pr_number,po_date, po_title, po_content)
VALUES(1,1,1,now(),"훗날에","삼겹살에 쌈장");

insert into poster(po_bo_number, po_ca_number, po_pr_number,po_date, po_title, po_content)
VALUES(1,1,1,now(),"야구이거","사실 잘몰라용");

select * from poster;