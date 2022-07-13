
CREATE TABLE Restaurant (
   rId                  BIGINT         PRIMARY KEY AUTO_INCREMENT,
   resName             VARCHAR(40)      NOT NULL,
   resLocation            VARCHAR(200)   NOT NULL,
   resScore            DOUBLE         NOT NULL,
   foodType            VARCHAR(10)      NOT NULL,
   foodPrice            INT            NOT NULL,
   resCapacity            INT            NOT NULL,
   ambianceComfort       INT,
   ambianceLux            INT,
   ambianceDate          INT,
   ambianceCost         INT,
   ambianceGroup         INT,
   ambianceComfortScore   DOUBLE         DEFAULT 0.0,
   ambianceLuxScore       DOUBLE         DEFAULT 0.0,
   ambianceDateScore       DOUBLE         DEFAULT 0.0,
   ambianceCostScore       DOUBLE         DEFAULT 0.0,
   ambianceGroupScore       DOUBLE         DEFAULT 0.0
) AUTO_INCREMENT = 2001;
CREATE TABLE User (
	uId			BIGINT			PRIMARY KEY AUTO_INCREMENT,
	userName 	VARCHAR(10)		NOT NULL,
	userId		VARCHAR(20)		NOT NULL,
	passwd		VARCHAR(20)		NOT NULL,
	nickName	VARCHAR(20)		NOT NULL,
	point		INT				NOT NULL,
	grade		VARCHAR(10)		NOT NULL,
	positien 	CHAR(1)			NOT NULL		DEFAULT 'U'
) AUTO_INCREMENT = 1001;

SELECT * FROM User;

SELECT * FROM Restaurant;

CREATE TABLE Point (
	pId				BIGINT			PRIMARY KEY AUTO_INCREMENT,
	userId 			BIGINT			NOT NULL,
	point			INT				NOT NULL,
	
	CONSTRAINT Point_userId_FK FOREIGN KEY (userId) REFERENCES User(uId)
) AUTO_INCREMENT = 3001;

SELECT * FROM Point;


CREATE TABLE Review (
	reviewId		BIGINT			PRIMARY KEY AUTO_INCREMENT,
	userId 			BIGINT			NOT NULL,
	resId			BIGINT			NOT NULL,
	reviewText		MEDIUMTEXT		NOT NULL,
	regDate			TIMESTAMP		NOT NULL 		DEFAULT CURRENT_TIMESTAMP,
	recommandScore	INT				NOT NULL,
	
	CONSTRAINT Review_userId_FK FOREIGN KEY (userId) REFERENCES User(uId),
	CONSTRAINT Review_resId_FK FOREIGN KEY (resId) REFERENCES Restaurant(rId)
	
) AUTO_INCREMENT = 4001;

SELECT * FROM Review;


