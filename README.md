# Spring Boot + MyBatis + Security
* ## Redis
* ## EHCache

## Use
* **Spring Boot + MyBatis** : CRUD 게시판 구현
* **Security** : 사용자 권한 (로그인 구현)
* **Redis** : 일정 조회수 이상의 글 따로 저장 및 갱신
* **EHCache** : 게시판 전체 목록 조회 성능 개선 테스트

## GET 방식과 POST 방식에 대한 상식 ?
* **POST 방식이 GET 방식보다 보안 측면에서 더 좋다 ?**
    * POST든 GET이든 보내는 데이터는 전부 클라이언트 측에서 볼수 있다. 단지 GET 방식은 URL에 데이터가 표시되어 별다른
    노력없이 볼 수 있는 것 뿐이다. 두 방식 모두 보안을 생각한다면 암호화해야 한다.
* **GET 방식이 POST 방식보다 빠르다 ?**
    * 빠르다. 그 이유는 GET 방식의 요청은 Caching(한번 접근 후, 또 요청할 시 빠르게 접근하기 위해 데이터를 저장)때문에 
    빠른 것이다.

### 메모
* TEST를 위해 Postman Interceptor를 Chrome 확장 프로그램으로 설치하고 Chrome에서 테스트할 경우 Controller가 2번씩
실행되는 현상 발생. Postman과 Edge로 테스트하면 정상.