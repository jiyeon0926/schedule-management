-- 테이블 생성
CREATE TABLE `schedule` (
  `scheduleId` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `content` varchar(200) NOT NULL,
  `name` varchar(20) NOT NULL,
  `password` varchar(100) NOT NULL,
  `dtcreate` date NOT NULL,
  `dtmodify` date NOT NULL,
  PRIMARY KEY (`scheduleId`)
);

CREATE TABLE `writer` (
  `email` varchar(50) NOT NULL,
  `name` varchar(20) NOT NULL,
  `password` varchar(100) NOT NULL,
  PRIMARY KEY (`email`)
);

ALTER TABLE schedule ADD CONSTRAINT schedule_writer_FK FOREIGN KEY (email) REFERENCES writer(email);

-- 일정 등록 (외래키로 인해 작성자 테이블에 등록된 사람만 일정 등록 가능)
INSERT INTO writer (email, name, password) VALUES('python12@naver.com', 'PythonLee', 'py1234');
INSERT INTO schedule (scheduleId, email, content, name, password, dtcreate, dtmodify) VALUES(0, 'python12@naver.com', 'ERD 마무리하기', 'PythonLee', 'py1234', '2024-10-31', '2024-10-31');

-- 전체 일정 조회
SELECT 	scheduleId, 
	email, 
	content, 
	name, 
	password, 
	dtcreate, 
	dtmodify
FROM schedule;

-- 선택 일정 조회
SELECT 	scheduleId, 
	email, 
	content, 
	name, 
	password, 
	dtcreate, 
	dtmodify
FROM schedule
WHERE scheduleId = 1

-- 선택 일정 수정
UPDATE schedule 
SET content='4 주차 강의 듣고 설계 진행하기'
WHERE scheduleId = 1;

-- 선택 일정 삭제
DELETE FROM schedule
WHERE scheduleId = 1;
