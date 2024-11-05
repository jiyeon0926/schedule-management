# 📅 일정 관리 앱
- 일정 작성, 수정, 조회 시 반환 받은 일정 정보에 비밀번호는 제외
- 일정 수정, 삭제 시 선택한 일정의 비밀번호와 요청할 때 함께 보낸 비밀번호가 일치할 경우에만 가능
  - 비밀번호가 일치하지 않을 경우, 적절한 오류 코드 및 메세지를 반환
- 일정 생성, 수정, 삭제 시 적절한 HTTP 상태코드를 반환

# ⭐ 구현 STEP
- Lv 1. 일정 생성 및 조회
- Lv 2. 일정 수정 및 삭제
- Lv 3. 연관 관계 설정
- Lv 4. 페이지네이션
- Lv 5. 예외 처리
- Lv 6. null 체크 및 특정 패턴에 대한 검증 수행

# 📌 구현할 때 지킬 것
- 디렉토리 및 파일의 적절한 분리
- 변수명 등 코드를 직관적이고 이해하기 쉽게 작성
- 주석을 활용해 코드를 설명
- 특수 상황에 대한 예외 처리
- 커밋 컨벤션을 지킨 커밋
- 3 Layer Architecture 에 따라 각 Layer의 목적에 맞게 개발
- CRUD 필수 기능은 모두 데이터베이스 연결 및 JDBC 를 사용해서 개발 (JPA 사용 금지)

# 📝 참고
- https://github.com/9898s/account-api/blob/master/README.md (API 명세서)
- https://skopenapi.readme.io/reference/api-%EA%B0%9C%EC%9A%94-1 (API 명세서)
- https://developers.naver.com/docs/login/calendar-api/calendar-api.md (API 명세서)
- https://cocococo.tistory.com/entry/Java-%EB%82%A0%EC%A7%9C-%EB%B0%8F-%EC%8B%9C%EA%B0%84-API-Date-and-Time-API-%EC%82%AC%EC%9A%A9-%EB%B0%A9%EB%B2%95 (날짜)
- https://velog.io/@hajinheee/Postman-Request-body%EC%97%90-timestamp-%EC%B0%8D%EC%96%B4-%EB%B3%B4%EB%82%B4%EB%8A%94-%EB%B0%A9%EB%B2%95 (postman 날짜 요청)
- https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide (API 명세서)
- https://velog.io/@cil05265/E-R-%EB%8B%A4%EC%9D%B4%EC%96%B4%EA%B7%B8%EB%9E%A8ERD-%EC%9E%91%EC%84%B1-%ED%91%9C%EA%B8%B0%EB%B2%95-%EB%B0%8F-%EC%98%88%EC%8B%9C (ERD)
- https://hipopatamus.tistory.com/116 (ERDCloud 사용 방법)
- https://velog.io/@k904808/%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4%EC%9D%98-%EC%84%A4%EA%B3%84 (ERD 논리/물리 이름 설정)
- https://freehoon.tistory.com/60 (ERD 관계)
- https://humblego.tistory.com/18 (DELETE 응답 상태 코드)
- https://okky.kr/questions/1394072 (DELETE 응답 상태 코드)
-------------

# 📄 API 명세서
|기능|Method|URL|HTTP 상태 코드 (성공)|HTTP 상태 코드 (실패)|
|:---|:---:|:---|:---|:---|
|일정 등록|POST|/schedules|201 Created|400 Bad Request|
|일정 단건 조회|GET|/schedules/{scheduleId}|200 OK|404 Not Found|
|일정 전체 조회|GET|/schedules|200 OK|빈 배열로 응답하되 상태 코드는 200 OK|
|일정 수정|PATCH|/schedules/{scheduleId}|200 OK|400 Bad Request|
|일정 삭제|DELETE|/schedules/{scheduleId}|204 No Content|404 Not Found|

|이름|타입|설명|필수 여부|
|:---|:---|:---|:---:|
|scheduleid|long|식별자|N|
|content|string|할일|Y|
|name|string|작성자명|Y|
|password|string|비밀번호|Y|
|email|string|이메일|Y|
|dtcreate|date|작성일 YYYY-MM-DD|N|
|dtmodify|date|수정일 YYYY-MM-DD|N|

### 1. 일정 등록
#### 1-1. Request
```
POST /schedules HTTP/1.1
Content-Type: application/json

{
 "content": "4 주차 강의까지 듣기",
 "name": "김자바",
 "password": "spring1234",
 "email": "spring00@gmail.com",
 "dtcreate": "2024-10-30",
 "dtmodify": "2024-10-30"
}
```
#### 1-2. Success Response
```
HTTP/1.1 201 Created
Content-Type: application/json
Location: /schedules/1

{
 "scheduleid": 1
 "content": "4 주차 강의까지 듣기",
 "name": "김자바",
 "password": "spring1234",
 "email": "spring00@gmail.com",
 "dtcreate": "2024-10-30",
 "dtmodify": "2024-10-30"
}
```
#### 1-3. Error Response
```
{
 "timestamp": "2024-10-30T17:22:33.123+00:00"
 "status": 400
 "error": Bad Request
 "path": /schedules
}
```

### 2. 일정 단건 조회
#### 2-1. Request
```
GET /schedules/1 HTTP/1.1
Host: localhost:8080
```
#### 2-2. Success Response
```
HTTP/1.1 200 OK
Content-Type: application/json

{
 "scheduleid": 1
 "content": "4 주차 강의까지 듣기",
 "name": "김자바",
 "password": "spring1234",
 "email": "spring00@gmail.com",
 "dtcreate": "2024-10-30",
 "dtmodify": "2024-10-30"
}
```
#### 2-3. Error Response
```
{
 "timestamp": "2024-10-30T17:22:33.123+00:00"
 "status": 404
 "error": Not Found
 "path": /schedules/1
}
```

### 3. 일정 전체 조회
#### 3-1. Request
```
GET /schedules HTTP/1.1
Host: localhost:8080
```
#### 3-2. Success Response
```
HTTP/1.1 200 OK
Content-Type: application/json

[
 {
  "scheduleid": 1
  "content": "4 주차 강의까지 듣기",
  "name": "김자바",
  "password": "spring1234",
  "email": "spring00@gmail.com",
  "dtcreate": "2024-10-30",
  "dtmodify": "2024-10-30"
 },
 {
  "scheduleid": 2
  "content": "api 명세서 작성하기",
  "name": "김명세",
  "password": "api1234",
  "email": "api00@gmail.com",
  "dtcreate": "2024-10-30",
  "dtmodify": "2024-10-30"
 }
]
```
#### 3-3. Error Response
```
{
 "timestamp": "2024-10-30T17:22:33.123+00:00"
 "status": 404
 "error": Not Found
 "path": /schedules
}
```

### 4. 일정 수정
#### 4-1. Request
```
PATCH /schedules/2 HTTP/1.1
Content-Type: application/json

{
  "content": "api 명세서 및 ERD 작성하기"
}
```
#### 4-2. Success Response
```
HTTP/1.1 200 OK
Content-Type: application/json
Location: /schedules/2

{
  "scheduleid": 2
  "content": "api 명세서 및 ERD 작성하기",
  "name": "김명세",
  "password": "api1234",
  "email": "api00@gmail.com",
  "dtcreate": "2024-10-30",
  "dtmodify": "2024-10-30"
}
```
#### 4-3. Error Response
```
{
 "timestamp": "2024-10-30T17:22:33.123+00:00"
 "status": 400
 "error": Bad Request
 "path": /schedules/2
}
```

### 5. 일정 삭제
#### 5-1. Request
```
DELETE /schedules/1 HTTP/1.1
Host: localhost:8080
```
#### 5-2. Success Response
```
HTTP/1.1 204 No Content
```
#### 5-3. Error Response
```
{
 "timestamp": "2024-10-30T17:22:33.123+00:00"
 "status": 404
 "error": Not Found
 "path": /schedules/1
}
```
-----

# ☁ ERD
- ERD Tool : [ERDCloud](https://www.erdcloud.com/)
- Entity : 작성자, 일정
- 작성자 Attribute : 작성자명, 비밀번호, 이메일
- 일정 Attribute : 할일, 작성자명, 비밀번호, 이메일, 작성일, 수정일
- 작성자와 일정은 1:N 관계
- email : 작성자 테이블의 기본키, 일정 테이블의 외래키
- scheduleId : 일정 테이블의 기본키

<img src="https://github.com/user-attachments/assets/9f7d8135-32e0-4bb3-9055-9d551999e27e" width="700" height="530">

------------

# 1️⃣ Lv 1. 일정 생성 및 조회

### 📋 요구사항
- 일정 생성
  - 할일, 작성자명, 비밀번호, 작성/수정일을 저장
  - 작성/수정일은 날짜와 시간을 모두 포함한 형태
  - 각 일정의 고유 식별자(ID)를 자동으로 생성하여 관리
  - 최초 입력 시, 수정일은 작성일과 동일
- 전체 일정 조회
  - 수정일 형식 : YYYY-MM-DD
  - 수정일 기준 내림차순으로 정렬하여 조회
- 선택 일정 조회
------

# 2️⃣ Lv 2. 일정 수정 및 삭제

### 📋 요구사항
- 선택한 일정 수정
  - 선택한 일정 내용 중 할일, 작성자명 만 수정 가능
  - 서버에 일정 수정을 요청할 때 비밀번호를 함께 전달
  - 작성일 은 변경할 수 없으며, 수정일 은 수정 완료 시, 수정한 시점으로 변경
- 선택한 일정 삭제
--------

# 3️⃣ Lv 3. 연관 관계 설정

### 📋 요구사항
- 작성자와 일정의 연결
  - 작성자를 식별하기 위해 이름으로만 관리하던 작성자에게 고유 식별자를 부여
  - 작성자 테이블을 생성하고 일정 테이블에 FK를 생성해 연관관계를 설정
  - 작성자는 이름 외에 이메일, 등록일, 수정일 정보를 가지고 있음
  - 고유 식별자를 통해 작성자를 조회할 수 있도록 기존 코드를 변경
  - 작성자의 고유 식별자가 일정 테이블의 외래키가 될 수 있도록 함
------

# 4️⃣ Lv 4. 페이지네이션

### 📋 요구사항
- 많은 양의 데이터를 효율적으로 표시하기 위해 데이터를 여러 페이지로 나눔
- 페이지 번호와 페이지 크기를 쿼리 파라미터로 전달하여 요청하는 항목을 나타냄
- 전달받은 페이지 번호와 크기를 기준으로 쿼리를 작성하여 필요한 데이터만을 조회하고 반환
- 등록된 일정 목록을 페이지 번호와 크기를 기준으로 모두 조회
- 조회한 일정 목록에는 작성자 이름이 포함
- 범위를 넘어선 페이지를 요청하는 경우 빈 배열을 반환
------

# 5️⃣ Lv 5. 예외 처리

### 📋 요구사항
- 예외 상황에 대한 처리를 위해 HTTP 상태 코드(링크)와 에러 메시지를 포함한 정보를 사용하여 예외를 관리
  - 수정, 삭제 시 요청할 때 보내는 비밀번호가 일치하지 않을 때 예외가 발생
  - 선택한 일정 정보를 조회할 수 없을 때 예외가 발생
------

# 6️⃣ Lv 6. null 체크 및 특정 패턴에 대한 검증 수행

### 📋 요구사항
- 유효성 검사
  - 할일은 최대 200자 이내로 제한, 필수값 처리
  - 비밀번호는 필수값 처리
  - 작성자의 이메일 정보가 형식에 맞는지 확인 
