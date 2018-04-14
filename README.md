# Spring Boot + MyBatis + Security
* ## Redis
* ## EHCache

## 시작하기
* IDE: IntelliJ
* OS: Windows10
* Java: Version 8
* pom.xml

```
<dependencies>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-redis</artifactId>
		</dependency>
		<dependency>
			<groupId>redis.clients</groupId>
			<artifactId>jedis</artifactId>
		</dependency>
		
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-web</artifactId>
			<version>5.0.4.RELEASE</version>
		</dependency>
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-config</artifactId>
			<version>5.0.4.RELEASE</version>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter</artifactId>
			<version>1.3.2</version>
		</dependency>

		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-tomcat</artifactId>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>org.apache.tomcat.embed</groupId>
			<artifactId>tomcat-embed-jasper</artifactId>
			<version>8.5.20</version>
		</dependency>

		<!-- spring-boot-starter-cache는 캐시 관련 설정을 편리하게 지원해주는 패키지 -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-cache</artifactId>
			<version>1.4.0.RELEASE</version>
		</dependency>
		<!--
		spring-boot-starter-cache는 기본 CacheManager로 ConcurrentHashMap을 사용하고 있어서
		Ehcache 2.x로 교체하기 위해 직접 의존성을 추가
		 -->
		<dependency>
			<groupId>net.sf.ehcache</groupId>
			<artifactId>ehcache</artifactId>
			<version>2.10.3</version>
		</dependency>

	</dependencies>
```

## 사용된 도구
* Spring Boot + MyBatis : CRUD 게시판 구현
* Security : 사용자 권한 (로그인 구현)
* Maven - 의존성 관리 프로그램
* Redis : 일정 조회수 이상의 글 따로 저장 및 갱신
* EHCache : 게시판 전체 목록 조회 성능 개선 테스트
* MySQL : 주 데이터베이스
* Tomcat : 웹 애플리케이션 서버

## 기타 메모
### GET 방식과 POST 방식에 대한 상식 ?
* **POST 방식이 GET 방식보다 보안 측면에서 더 좋다 ?**
    * POST든 GET이든 보내는 데이터는 전부 클라이언트 측에서 볼수 있다. 단지 GET 방식은 URL에 데이터가 표시되어 별다른
    노력없이 볼 수 있는 것 뿐이다. 두 방식 모두 보안을 생각한다면 암호화해야 한다.
* **GET 방식이 POST 방식보다 빠르다 ?**
    * 빠르다. 그 이유는 GET 방식의 요청은 Caching(한번 접근 후, 또 요청할 시 빠르게 접근하기 위해 데이터를 저장)때문에 
    빠른 것이다.
    
### Postman & Postman Interceptor
* TEST를 위해 Postman Interceptor를 Chrome 확장 프로그램으로 설치하고 Chrome에서 테스트할 경우 Controller가 2번씩
실행되는 현상 발생. Postman과 Edge로 테스트하면 정상.

## 저자
* **조민국** - *초기작* - [MinGOODdev](https://github.com/MinGOODdev)

## 감사 인사

* [Redis 저장소에 CRUD 로직 구현하기](http://jsonobject.tistory.com/390?category=787905)
* [Spring Boot에서 Redis 사용하기](http://kingbbode.tistory.com/25)
* [EHCache를 이용한 Cache 구현](http://javacan.tistory.com/entry/133)

---


