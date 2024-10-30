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
-------------

# 📄 API 명세서
|기능|Method|URL|HTTP 상태 코드|
|:---:|:---:|:---:|:---:|
|일정 등록|POST|/posts|201 Created|
|일정 단건 조회|GET|/posts/{postId}|200 OK|
|일정 전체 조회|GET|/posts|200 OK|
|일정 수정|PATCH|/posts/{postId}|200 OK|
|일정 삭제|DELETE|/posts/{postId}|200 OK|
