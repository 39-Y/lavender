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
![서비스구조](https://user-images.githubusercontent.com/77563814/134013439-f36295cc-39c0-41e7-86b6-19e6a02183c6.jpg)


## 기술 스택

- Front
    - Javascript, Jquery, CSS, HTML, ThymeLeaf
- Back
    - Java - Java-version 16, SpringBoot, Spring Data JPA, Maven


## 개발 기간

- 2021.3.27 ~ 5.02  (5주)
    

## 기획 & 설계

[기능 명세서](https://www.notion.so/4241cfb8aab64592af099f34b2ccb938)

[페이지 기획서](https://whimsical.com/8-MbpuashuB5aRgSKR6jM14A) → ✨[디자인](https://www.figma.com/file/1FrTtdMDvn53kDvS93GHBL/%EC%B9%B4%ED%8E%98?node-id=0%3A1)

[API 명세서](https://www.notion.so/API-0b0cbd9ff7eb46d4b4b21446bf20233d)

[API 문서](https://www.notion.so/API-f730b73b41b249a8a394cbbc4dc18213)

[DB 명세서](https://www.notion.so/DB-45d7f01cbc334d40968bd39d2dfe84ad)

![DB](https://user-images.githubusercontent.com/77563814/133954614-b1a28410-baac-4f6b-a1e0-3c35b5d5d93b.png)
