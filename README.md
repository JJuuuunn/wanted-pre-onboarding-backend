# wanted-pre-onboarding-backend

원티드 프리온보딩 백엔드 인턴십 과제

## 소개

- 양성준
- didtdjwns111@gmail.com

## 개발 환경

- Java 11
- Spring Boot 2.7.14

## 라이브러리 및 프레임워크

- Spring Data JPA
- Spring Security
- Spring Validation
- Lombok
- Jwt

## 엔드포인트 호출 방법
| HTTP Method | End point         | Description |
|-------------|-------------------| --- |
| POST        | /member/join      | 회원가입 |
| POST        | /member/login     | 로그인 |
| POST        | /post/write       | 게시물 등록 |
| GET         | /post/list        | 게시물 목록 |
| GET         | /post/{id}        | 게시물 조회 |
| PUT         | /post/{id}/modify | 게시물 수정 |
| DELETE      | /post/{id}/delete | 게시물 삭제 |


## 테이블 구조
![테이블 구조(member).png](테이블 구조(member).png)

![테이블 구조(post).png](테이블 구조(post).png)

## 시연 영상

[링크](https://drive.google.com/file/d/1N7yz_fYo5OcK_Cuc0mj_nnoKcYW_5VaZ/view?usp=drive_link)

## 구현 방법
 - Docker Compose 를 통해 Mysql 이미지 생성
 - Spring Security 와 JWT 토큰을 사용하여 사용자 인증 처리
 - Global Exception Handler 를 사용하여 WantedException 과 MethodArgumentNotValidException 예외 처리
 - postman 을 사용하여 API 테스트 및 실행

## API 명세
| HTTP Method | End point         | Description | Request                       | Response                |
|-------------|-------------------|-------------|-------------------------------|-------------------------|
| POST        | /member/join      | 회원가입        | MemberJoinRequest             | MemberJoinResponse      |
| POST        | /member/login     | 로그인         | MemberLoginRequest            | MemberLoginResponse     |
| POST        | /post/write       | 게시물 등록      | PostWriteRequest              | PostResponse            |
| GET         | /post/list        | 게시물 목록      | Pageable                      | Page<PostResponse>      |
| GET         | /post/{id}        | 게시물 조회      | int page                      | PostResponse            |
| PUT         | /post/{id}/modify | 게시물 수정      | int postId, PostModifyRequest | PostResponse            |
| DELETE      | /post/{id}/delete | 게시물 삭제      | int postId                    | String message("삭제 성공") |