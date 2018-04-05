# Spring Boot + MyBatis + Security
* ### Redis
* ### EHCache

### 구성
* Spring Boot + MyBatis : CRUD 게시판 구현
* Securty : 사용자 권한 (로그인 구현)
* Redis : 일정 조회수 이상의 글 따로 저장 및 갱신
* EHCache : 게시판 전체 목록 조회 성능 개선 테스트

아래부터 Wiki에 옮길 내용
### EHCache
게시판이나 블로그 등 웹 기반의 애플리케이션은 최근에 사용된 데이터가 또 다시 사용되는 경향을 갖고 있다.<br/>
80:20 법칙에 따라 20%의 데이터가 전체 조회 건수의 80%를 차지할 경우 캐시를 사용함으로써 성능을 대폭 향상시킬 수 있을 것이다.

* 주요 특징
  1. 경량의 빠른 캐시 엔진
  2. 확장(scable) - 메모리 & 디스크 저장 지원, 멀티 CPU의 동시 접근에 튜닝
  3. 분산 지원 - 동기/비동기 복사, 피어(peer) 자동 발견
  4. 높은 품질 - Hibernate, Confluence, Spring 등에서 사용되고 있으며, Gaia 컴포넌트에서도 EHCache를 사용하여 Cache를 구현하였다.

* 기본 사용법
EHCache를 사용하기 위해서는 다음과 같은 작업이 필요하다.
  1. EHCache를 설치 및 Cache 설정 파일 작성
      * 클래스 패스에 위치한 ehcache.xml 파일로부터 Cache 설정 정보를 로딩한다.
      * 클래스 패스에 위차한 ehcache.xml 파일이 아닌 다른 파일 설정을 사용하고 싶다면 다음과 같이 URL InputStream 또는 String(경로) 객체를 사용하여 설정 파일의 위치를 지정할 수 있다.
      * <pre>URL configFile = this.getClass().getResource("/ehcache_config_replicate.xml")
        CacheManager cacheManager = new CacheManager(configFile);</pre>
  2. CacheManager 생성
  3. CacheManager로부터 구한 Cache를 이용해 CRUD 작업 수행
  4. CacheManager 종료
  
* 분산 캐시
  1. EHCache는 분산 캐시를 지원한다.
  2. EHCache는 피어(peer) 자동 발견 및 RMI를 이용한 클러스터 간 데이터 전송의 신뢰성 등 분산 캐시를 위한 완전한 기능을 제공하고 있다.
  3. RMI를 이용하여 분산 캐시를 구현하고 있기 때문에 Serializable한 객체만 분산 캐시에서 사용 가능하다. 키 역시 Serializable해야 한다.

