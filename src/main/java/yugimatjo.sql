CREATE TABLE User (
	uId			BIGINT			PRIMARY KEY AUTO_INCREMENT,
	userName 	VARCHAR(20)		NOT NULL,
	userId		VARCHAR(20)		NOT NULL,
	passwd		VARCHAR(20)		NOT NULL,
	nickName	VARCHAR(20)		NOT NULL,
	grade		VARCHAR(20)		NOT NULL		DEFAULT '메추리알',
	position 	CHAR(1)			NOT NULL		DEFAULT 'U'
) AUTO_INCREMENT = 1001;

INSERT INTO User (userName, userId, passwd, nickName)
VALUES ('박희정','park77','cc111','heejung');

CREATE TABLE Restaurant (
   rId                 BIGINT         PRIMARY KEY AUTO_INCREMENT,
   resName             VARCHAR(50)    NOT NULL,
   resScore            DOUBLE         NOT NULL,
   foodType            VARCHAR(30)    NOT NULL,
   foodPrice           INT            NOT NULL,
   resCapacity         INT            NOT NULL
) AUTO_INCREMENT = 2001;

SELECT * FROM User;

SELECT * FROM Restaurant;

SELECT * FROM Account;

SELECT * FROM Review;

SELECT * FROM Location;

SELECT * FROM Ambiance;


CREATE TABLE Account (
	pId				BIGINT			PRIMARY KEY AUTO_INCREMENT,
	userId 			BIGINT			NOT NULL,
	point			INT				NOT NULL		DEFAULT 0,
	
	CONSTRAINT Account_userId_FK FOREIGN KEY (userId) REFERENCES User(uId)
) AUTO_INCREMENT = 3001;


CREATE TABLE Review (
	reviewId		BIGINT			PRIMARY KEY AUTO_INCREMENT,
	userId 			BIGINT			NOT NULL,
	resId			BIGINT			NOT NULL,
	reviewText		MEDIUMTEXT		NOT NULL,
	regDate			TIMESTAMP		NOT NULL 		DEFAULT CURRENT_TIMESTAMP,
	recommandScore	INT				NOT NULL		DEFAULT 0,
	
	CONSTRAINT Review_userId_FK FOREIGN KEY (userId) REFERENCES User(uId),
	CONSTRAINT Review_resId_FK FOREIGN KEY (resId) REFERENCES Restaurant(rId)
) AUTO_INCREMENT = 4001;



CREATE TABLE Location (
	lId				BIGINT			PRIMARY KEY AUTO_INCREMENT,
	regionName 		VARCHAR(50)		NOT NULL,
	resId			BIGINT			NOT NULL,
	
	CONSTRAINT Location_resId_FK FOREIGN KEY (resId) REFERENCES Restaurant(rId)
) AUTO_INCREMENT = 5001;



CREATE TABLE Ambiance (
	aId				BIGINT			PRIMARY KEY AUTO_INCREMENT,
	resId 			BIGINT			NOT NULL,
	comfort			INT				NOT NULL		DEFAULT 0,
	luxury			INT				NOT NULL		DEFAULT 0,
	cost			INT				NOT NULL		DEFAULT 0,
	dating			INT				NOT NULL		DEFAULT 0,
	family			INT				NOT NULL		DEFAULT 0,
	comfortScore	INT				NOT NULL		DEFAULT 0,
	luxuryScore		INT				NOT NULL		DEFAULT 0,
	costScore		INT				NOT NULL		DEFAULT 0,
	datingScore		INT				NOT NULL		DEFAULT 0,
	familyScore		INT				NOT NULL		DEFAULT 0,
	
	CONSTRAINT Ambiance_resId_FK FOREIGN KEY (resId) REFERENCES Restaurant(rId)
) AUTO_INCREMENT = 6001;






