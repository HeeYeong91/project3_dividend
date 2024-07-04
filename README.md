# :dollar: 배당금 프로젝트
<br />

## :page_facing_up: 목차
1. 프로젝트 소개
2. 프로젝트 기능
   * [1. 회원 가입 API](#1-회원-가입-API)
   * [2. 로그인 API](#2-로그인-API)
   * [3. 배당금 조회 API](#3-배당금-조회-API)
   * [4. 회사 추가 API](#4-회사-추가-API)
   * [5. 회사 리스트 조회 API](#5-회사-리스트-조회-API)
   * [6. 회사 삭제 API](#5-회사-삭제-API)
<br />

## :eyes: 1. 프로젝트 소개
3차 실습 프로젝트 Spring boot와 Java를 활용하여 주식 배당 내역을 스크래핑하여 보여주는 프로젝트 <br />

티커로 회사를 스크래핑하고 <br />
회사에 대한 배당 내역을 스크래핑해서 보여주는 프로젝트 <br />
<br />

:smile: 개발 인원 : 1명 <br />
:calendar: 프로젝트 기간 : 2024년 7월 1일 ~ 2024년 7월 4일 <br />
:hammer: Tools <br /> 
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white) 
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white) 
![Redis](https://img.shields.io/badge/redis-%23DD0031.svg?style=for-the-badge&logo=redis&logoColor=white) <br />
:books: languages & library <br />
![Spring](https://img.shields.io/badge/springboot3.2.7-%236DB33F.svg?style=for-the-badge&logo=springboot&logoColor=white) 
![Java](https://img.shields.io/badge/java17-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)
![Junit5](https://img.shields.io/badge/Junit5-%23C21325?style=for-the-badge&logo=junit5&logoColor=white)
![Jpa](https://img.shields.io/badge/Jpa-%236DB33F.svg?style=for-the-badge)
![Lombok](https://img.shields.io/badge/Lombok-%23ffffff.svg?style=for-the-badge) <br />

<br />

프로젝트 엔티티 구조
![엔티티구조](https://github.com/HeeYeong91/project3_dividend/assets/139057065/a827e517-b315-434d-8cc6-821c2cb8e7bc) <br />

## :pushpin: 2. 프로젝트 기능 및 Api 명세

## 회원 관련 API
![회원관련API](https://github.com/HeeYeong91/project3_dividend/assets/139057065/c89ec1ae-3f4d-4e16-9cc8-179adfb2c655) <br />

## 1. 회원 가입 API
* ### 사용자로 부터 회원가입에 필요한 이름, 비밀번호 입력받아 회원가입
* ### PasswordEncoder를 이용해 사용자가 입력한 비밀번호를 암호화해서 DB에 저장
* ### 회원가입 시 권한 부여 (ROLE_WRITE, ROLE_READ) <br /><br />
![회원가입](https://github.com/HeeYeong91/project3_dividend/assets/139057065/c5d46ee4-75a4-4726-a515-b46d8b3a8f16) <br />
<br />

## 2. 로그인 API
* ### 이름과 비밀번호를 입력받아 로그인
* ### JWT를 이용해 로그인 시 토큰 발급
* ### Spring security를 이용해 토큰이 없으면 로그인, 회원가입, H2-console을 제외한 접근 제한 <br /><br />
![토큰생성](https://github.com/HeeYeong91/project3_dividend/assets/139057065/348cae8f-4dfe-4bbf-ac03-ee909699d475) <br />
![로그인](https://github.com/HeeYeong91/project3_dividend/assets/139057065/0c7507fb-cfdf-42d3-9597-d1f5aa07c851) <br />
<br />

## 배당 관련 API
![배당관련API](https://github.com/HeeYeong91/project3_dividend/assets/139057065/8aa0e9cb-bbc7-41dc-a122-e47850a499da)

## 3. 배당금 조회 API
* ### 회사명을 기준으로 회사 정보를 조회
* ### 조회된 회사 ID로 배당금 정보 조회
* ### 결과 조합 후 반환
* ### Redis를 이용해 배당 조회 결과를 캐싱 <br /><br />
![배당조회](https://github.com/HeeYeong91/project3_dividend/assets/139057065/77b110e3-8c86-435d-9d50-f0a2d8e3fa99) <br />
<br />

## 회사 관련 API
![회사관련API](https://github.com/HeeYeong91/project3_dividend/assets/139057065/39c479fd-e006-490e-8fa2-50bbfa194776) <br />

## 4. 회사 추가 API
* ### ticker를 기준으로 회사 스크래핑
* ### 해당 회사가 존재하면 회사의 배당금 정보 스크래핑
* ### 스크래핑한 회사 정보 DB에 저장
* ### Trie를 이용해 회사 이름 자동완성 기능 추가
* ### 회원에게 WRITE권한이 있어야 기능 사용가능 <br /><br />
![회사추가](https://github.com/HeeYeong91/project3_dividend/assets/139057065/79f5bfcd-c1b6-4d2d-8c2f-5c126f1f7d8e) <br />
<br />

## 5. 회사 리스트 조회 API
* ### 회사 리스트가 많을 경우를 대비해서 Pageable를 이용해 페이징처리한 리스트 결과 반환
* 회원에게 READ권한이 있어야 기능 사용가능 <br /><br />
![회사리스트조회](https://github.com/HeeYeong91/project3_dividend/assets/139057065/b08cc794-5ecf-47d3-b36e-2f99442fc51d) <br />
<br />

## 6. 회사 삭제 API
* ### 사용자에게 ticker를 입력받아 DB에서 회사 정보 삭제
* ### 삭제 시 Redis에 저장된 캐싱 데이터도 삭제
* ### 회원에게 WRITE권환이 있어야 기능 사용가능 <br /><br />
![회사삭제](https://github.com/HeeYeong91/project3_dividend/assets/139057065/05fe0aa8-abb7-4e39-a529-7d6edd0e3230) <br />
<br />

