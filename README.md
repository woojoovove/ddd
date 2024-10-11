# DDD

「도메인 주도 설계 철저 입문」8장에 대한 Java 실습 프로젝트입니다.

![image](https://github.com/user-attachments/assets/29925c1c-d8ce-4cd8-8fed-20c72927ffa7)

## 폴더 구조
![image](https://github.com/user-attachments/assets/62dafcc5-7211-4972-bd32-8761038a3427)

## 내용
DDD 주요 개념별 구현체
- 도메인 엔티티 `User`
- 값 객체 `UserID`, `UserName`
- 도메인 서비스 `UserService` : 엔티티의 중복 검사 등 도메인 지식을 담는 서비스
- 인터페이스를 통한 느슨한 결합 `IUserRepository`, `InMemoryUserRepository`
- 앱 서비스 `UserRegisterService`
- IoCContainer를 통한 DI : `IoCContainer`
- 단위 테스트 : `UserRegisterServiceTest`
