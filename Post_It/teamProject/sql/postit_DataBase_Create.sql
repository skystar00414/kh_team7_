-- Active: 1722306683503@@127.0.0.1@3306@postit
-- Active: 1722306683503@@127.0.0.1@3306
drop DATABASE if EXISTS postit;
create DATABASE if not exists postit;

use postit;

DROP TABLE IF EXISTS `privacy`;

CREATE TABLE `privacy` (
	`pr_number`	int auto_increment primary key	NOT NULL,
	`pr_id`	varchar(15)	NOT NULL,
	`pr_pw`	varchar(15)	NOT NULL,
	`pr_gender`	varchar(5)	NOT NULL,
	`pr_birth_day` varchar(15)	NOT NULL,
	`pr_phone`	varchar(20)	NOT NULL,
	`pr_email`	varchar(100)	NOT NULL,
	`pr_au_key`	varchar(5)	NOT NULL,
	`pr_failed`	int	NOT NULL	DEFAULT 0,
	`pr_st_key`	VARCHAR(10)	NOT NULL DEFAULT 'act',
	`pr_re_key`	varchar(50) NULL,
	`pr_autologin`	tinyint	NOT NULL	DEFAULT 0,
	`pr_stop`	datetime	NULL
);

DROP TABLE IF EXISTS `certification`;

CREATE TABLE `certification` (
	`certification_Key`	varchar(10)  primary key	NOT NULL,
	`certification_date`	DATETIME	NOT NULL,
	`certification_pr_number`	int	NOT NULL
);

DROP TABLE IF EXISTS `poster`;

CREATE TABLE `poster` (
	`po_number`	int auto_increment  primary key	NOT NULL,
	`po_bo_number`	int	NOT NULL,
	`po_ca_number`	int	NOT NULL,
	`po_pr_number`	int	NOT NULL,
	`po_date`	date	NOT NULL,
	`po_title`	varchar(50)	NOT NULL,
	`po_content`	longtext	NOT NULL,
	`po_views`	int	NOT NULL	DEFAULT 0,
	`po_reco`	int	NOT NULL	DEFAULT 0,
	`po_unreco`	int	NOT NULL	DEFAULT 0
);

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
	`ca_number`	int auto_increment  primary key	NOT NULL,
	`ca_bo_number`	int	NOT NULL,
	`ca_name`	varchar(10)	NOT NULL
);

DROP TABLE IF EXISTS `border`;

CREATE TABLE `border` (
	`bo_number`	int auto_increment  primary key	NOT NULL,
	`bo_title`	varchar(30)	NOT NULL,
	`bo_counter`	int	NOT NULL	DEFAULT 0,
	`bo_detail`	varchar(255)	NULL,
	`bo_oldtitle`	varchar(30)	NULL
);

DROP TABLE IF EXISTS `myPage`;

CREATE TABLE `myPage` (
	`my_id`	int primary key auto_increment	NOT NULL,
	`my_pr_number`	int	NOT NULL,
	`my_intro`	longtext	NULL
);

DROP TABLE IF EXISTS `postBucket`;

CREATE TABLE `postBucket` (
	`pB_id`	int auto_increment  primary key	NOT NULL,
	`pB_pr_number`	int	NOT NULL,
	`pB_po_number`	int	NOT NULL
);

DROP TABLE IF EXISTS `reply`;

CREATE TABLE `reply` (
	`reply_key`	int auto_increment  primary key	NOT NULL,
	`reply_pr_number`	int	NOT NULL,
	`reply_po_number`	int	NOT NULL,
	`reply_detail`	varchar(200)	NOT NULL,
	`reply_date`	datetime	NOT NULL,
	`reply_good`	int	NOT NULL	DEFAULT 0,
	`reply_report`	int	NOT NULL	DEFAULT 0
);

DROP TABLE IF EXISTS `authority`;

CREATE TABLE `authority` (
	`au_key`	varchar(10) primary key	NOT NULL,
	`au_name`	varchar(10)	NOT NULL
);

DROP TABLE IF EXISTS `stateList`;

CREATE TABLE `stateList` (
	`st_key`	VARCHAR(10)  primary key	NOT NULL,
	`st_name`	varchar(10)	NOT NULL
);

DROP TABLE IF EXISTS `reasonList`;

CREATE TABLE `reasonList` (
	`re_key`	varchar(50) primary key	NOT NULL
);

DROP TABLE IF EXISTS `reklist`;

CREATE TABLE `reklist` (
	`rek_key`	int auto_increment primary key	NOT NULL,
	`rek_po_number`	int	NOT NULL,
	`rek_pr_number`	int	NOT NULL
);

DROP TABLE IF EXISTS `notreklist`;

CREATE TABLE `notreklist` (
	`notrek_key`	int auto_increment primary key	NOT NULL,
	`notrek_po_number`	int	NOT NULL,
	`notrek_pr_number`	int	NOT NULL
);

ALTER TABLE `privacy` ADD CONSTRAINT `FK_authority_TO_privacy_1` FOREIGN KEY (
	`pr_au_key`
)
REFERENCES `authority` (
	`au_key`
);

ALTER TABLE `privacy` ADD CONSTRAINT `FK_stateList_TO_privacy_1` FOREIGN KEY (
	`pr_st_key`
)
REFERENCES `stateList` (
	`st_key`
);

ALTER TABLE `privacy` ADD CONSTRAINT `FK_reasonList_TO_privacy_1` FOREIGN KEY (
	`pr_re_key`
)
REFERENCES `reasonList` (
	`re_key`
);

ALTER TABLE `certification` ADD CONSTRAINT `FK_privacy_TO_certification_1` FOREIGN KEY (
	`certification_pr_number`
)
REFERENCES `privacy` (
	`pr_number`
);

ALTER TABLE `poster` ADD CONSTRAINT `FK_border_TO_poster_1` FOREIGN KEY (
	`po_bo_number`
)
REFERENCES `border` (
	`bo_number`
);

ALTER TABLE `poster` ADD CONSTRAINT `FK_category_TO_poster_1` FOREIGN KEY (
	`po_ca_number`
)
REFERENCES `category` (
	`ca_number`
);

ALTER TABLE `category` ADD CONSTRAINT `FK_border_TO_category_1` FOREIGN KEY (
	`ca_bo_number`
)
REFERENCES `border` (
	`bo_number`
);

ALTER TABLE `poster` ADD CONSTRAINT `FK_privacy_TO_poster_1` FOREIGN KEY (
	`po_pr_number`
)
REFERENCES `privacy` (
	`pr_number`
);

ALTER TABLE `myPage` ADD CONSTRAINT `FK_privacy_TO_myPage_1` FOREIGN KEY (
	`my_pr_number`
)
REFERENCES `privacy` (
	`pr_number`
);

ALTER TABLE `postBucket` ADD CONSTRAINT `FK_privacy_TO_postBucket_1` FOREIGN KEY (
	`pB_pr_number`
)
REFERENCES `privacy` (
	`pr_number`
);

ALTER TABLE `postBucket` ADD CONSTRAINT `FK_poster_TO_postBucket_1` FOREIGN KEY (
	`pB_po_number`
)
REFERENCES `poster` (
	`po_number`
);

ALTER TABLE `reply` ADD CONSTRAINT `FK_privacy_TO_reply_1` FOREIGN KEY (
	`reply_pr_number`
)
REFERENCES `privacy` (
	`pr_number`
);

ALTER TABLE `reply` ADD CONSTRAINT `FK_poster_TO_reply_1` FOREIGN KEY (
	`reply_po_number`
)
REFERENCES `poster` (
	`po_number`
);

ALTER TABLE `reklist` ADD CONSTRAINT `FK_poster_TO_reklist_1` FOREIGN KEY (
	`rek_po_number`
)
REFERENCES `poster` (
	`po_number`
);

ALTER TABLE `reklist` ADD CONSTRAINT `FK_privacy_TO_reklist_1` FOREIGN KEY (
	`rek_pr_number`
)
REFERENCES `privacy` (
	`pr_number`
);

ALTER TABLE `notreklist` ADD CONSTRAINT `FK_poster_TO_notreklist_1` FOREIGN KEY (
	`notrek_po_number`
)
REFERENCES `poster` (
	`po_number`
);

ALTER TABLE `notreklist` ADD CONSTRAINT `FK_privacy_TO_notreklist_1` FOREIGN KEY (
	`notrek_pr_number`
)
REFERENCES `privacy` (
	`pr_number`
);

