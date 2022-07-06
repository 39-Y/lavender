# Lavender: 답변형 게시판 웹사이트

❓ Problem : 게시글의 이미지 크기와 글꼴을 바꿀 수 있다면?😮

💯 Solution : Naver Edit API를 사용하여 게시판을 만들자!! 😁


## 스크린샷
![상세 게시글](https://user-images.githubusercontent.com/102135011/177038924-654c34f9-7a87-470d-935c-2b39ccedeb8d.png)
![메인 게시판 페이지](https://user-images.githubusercontent.com/102135011/177038942-9c926b29-459f-4e36-a876-2ec94519d35f.png)


## 주요 기능과 로직

- **로그인** : 구글, 네이버 OAuth 로그인 API 사용
- **페이징 기능** : 메인 페이지 검색 결과로 게시글 리스트를 백에서 프론트로 10개씩 보내줌
- **게시글 에디터** : 네이버 editor API 사용하여 이미지 업로드와 글꼴, 폰트 변경 가능
- **게시글 이미지 미리보기** : 이미지 업로드시 미리보기 가능
- **게시판** : 게시글 등록, 수정, 삭제, 답글 쓰기
- **프로필 설정 기능** : 유저 Role이 Guest라면 프로필 설정, 수정, 초기화
- **조회수 기능** : 해당 게시글을 본 횟수 DB에 저장

## 메인 로직 1 : 유저의 Role에 따라 게시글 권한 제한
1. 구글, 네이버 계정으로 로그인시 게시글 작성 가능
2. 유저의 Role이 Guest일 경우 게시글 작성 제한
3. 게시글 작성 후 작성자만이 수정, 삭제 가능
4. 상세페이지의 경우 모든 유저가 접근 가능

## 메인 로직 2 : 메인 페이지에서 게시글 확인

1. 페이징: 선택한 페이지의 게시글 정보들을 리스트로 DB에서 꺼내옴
2. 답글 확인 : 게시글에 답글이 달릴 경우 게시글 아래에 배치되어 순서대로 리스트 정렬
3. 작성자가 쓴 글 : 게시글의 작성자를 검색하여 리스트 정렬

## 서비스 구조
![서비스 구조](https://user-images.githubusercontent.com/102135011/177359675-989ff0f6-41a6-4da5-9313-5705d0222293.jpg)


## 기술 스택

- Front
    - Javascript, Jquery, CSS, HTML, ThymeLeaf
- Back
    - Java - Java-version 16, SpringBoot, Spring Data JPA, Maven, Oracle, MyBatis


## 개발 기간

- 2021.3.27 ~ 5.02  (5주)
    

## 기획 & 설계

[기능 명세서](https://www.notion.so/278e131dcd7b4b68bf8fc472e21052c6)

[API 명세서](https://www.notion.so/API-4f2dbb4b7246483dbf55127db7abdf3d)

[DB 명세서](https://www.notion.so/DB-d07dd3f074174ddfa9303856d2e32b64)

![ERD](https://user-images.githubusercontent.com/102135011/177540616-fec2e013-53a7-40e0-bb65-9e3668e344c2.png)
