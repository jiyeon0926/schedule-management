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
-------------

# 📄 API 명세서
|기능|Method|URL|HTTP 상태 코드|
|:---|:---:|:---|:---|
|일정 등록|POST|/schedules|201 Created|
|일정 단건 조회|GET|/schedules/{scheduleId}|200 OK|
|일정 전체 조회|GET|/schedules|200 OK|
|일정 수정|PATCH|/schedules/{scheduleId}|200 OK|
|일정 삭제|DELETE|/schedules/{scheduleId}|200 OK|

|이름|타입|설명|
|:---|:---|:---|
|content|string|할일|
|username|string|작성자명|
|password|string|비밀번호|
|email|string|이메일|
|dtcreate|datetime|작성일 YYYY-MM-DD|
|dtmodify|datetime|수정일 YYYY-MM-DD|

### 1. 일정 등록
#### 1-1. Request
```
POST /schedules HTTP/1.1
Content-Type: application/json

{
 "content": "4 주차 강의까지 듣기",
 "username": "김자바",
 "password": "spring1234",
 "email": "spring00@gmail.com",
 "dtcreate": "2024-10-30",
 "dtmodify": "2024-10-30"
}
```
#### 1-2. Response
```
HTTP/1.1 201 Created
Content-Type: application/json
Location: /schedules/1

{
 "content": "4 주차 강의까지 듣기",
 "username": "김자바",
 "password": "spring1234",
 "email": "spring00@gmail.com",
 "dtcreate": "2024-10-30",
 "dtmodify": "2024-10-30"
}
```

### 2. 일정 단건 조회
#### 2-1. Request
```
GET /schedules/1 HTTP/1.1
Host: localhost:8080
```
#### 2-2. Response
```
HTTP/1.1 200 OK
Content-Type: application/json

{
 "content": "4 주차 강의까지 듣기",
 "username": "김자바",
 "password": "spring1234",
 "email": "spring00@gmail.com",
 "dtcreate": "2024-10-30",
 "dtmodify": "2024-10-30"
}
```

### 3. 일정 전체 조회
#### 3-1. Request
```
GET /schedules HTTP/1.1
Host: localhost:8080
```
#### 3-2. Response
```
HTTP/1.1 200 OK
Content-Type: application/json

[
 {
  "scheduleid": 1
  "content": "4 주차 강의까지 듣기",
  "username": "김자바",
  "password": "spring1234",
  "email": "spring00@gmail.com",
  "dtcreate": "2024-10-30",
  "dtmodify": "2024-10-30"
 },
 {
  "scheduleid": 2
  "content": "api 명세서 작성하기",
  "username": "김명세",
  "password": "api1234",
  "email": "api00@gmail.com",
  "dtcreate": "2024-10-30",
  "dtmodify": "2024-10-30"
 }
]
```

### 4. 일정 수정
#### 4-1. Request
```
PATCH /schedules/2 HTTP/1.1
Content-Type: application/json

{
  "content": "api 명세서 및 ERD 작성하기",
}
```

#### 4-2. Response
```
HTTP/1.1 200 OK
Content-Type: application/json
Location: /schedules/2

{
  "scheduleid": 2
  "content": "api 명세서 및 ERD 작성하기",
  "username": "김명세",
  "password": "api1234",
  "email": "api00@gmail.com",
  "dtcreate": "2024-10-30",
  "dtmodify": "2024-10-30"
}
```

### 5. 일정 삭제
#### 5-1. Request
```
DELETE /schedules/1 HTTP/1.1
Host: localhost:8080
```
-----

# ☁ ERD
- Entity : 일정, 작성자
- 일정 Attribute : 할일, 작성자명, 비밀번호, 이메일, 작성일, 수정일
- 작성자 Attribute : 작성자명, 비밀번호, 이메일
