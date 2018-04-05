# Spring Boot + MyBatis + Security
* ## Redis
* ## EHCache

## Use
* Spring Boot + MyBatis : CRUD 게시판 구현
* Security : 사용자 권한 (로그인 구현)
* Redis : 일정 조회수 이상의 글 따로 저장 및 갱신
* EHCache : 게시판 전체 목록 조회 성능 개선 테스트

### 메모
* TEST를 위해 Postman Interceptor를 Chrome 확장 프로그램으로 설치하고 Chrome에서 테스트할 경우 Controller가 2번씩
실행되는 현상 발생. Postman과 Edge로 테스트하면 정상.