# 몰작교 - 우리를 잇는 오작교
![803d5e03-f432-4a67-b70d-0e6c3d8a1788](https://github.com/mummy-alive/Week2_front/assets/113423544/f62e65dc-b04a-49f8-8fda-5c3d16180474)


# 소개
---
## “팀원 구하셨나요?”

개발-잠-개발-잠만 반복하는 삶… 이번주 살기도 바쁜데 팀원은 언제 구하죠?

***몰입캠프 동기들의 관심사, 기술스택을 한 눈에 확인하고 나와 꼭 맞는 팀원을 구해요.***

# Team

---

이화여자대학교 조민주 - 백엔드, 서버

[mummy-alive - Overview](https://github.com/mummy-alive/)

성균관대학교 송종찬 - 프론트엔드

[jongchan159 - Overview](https://github.com/jongchan159)

# Outline

---
![Frame 1](https://github.com/mummy-alive/Week2_front/assets/113423544/56c0b6cb-8f3d-4917-8101-8d6d1c29e59d)


### 1번탭:

- 2번탭, 3번탭 Preview

### 2번탭: 팀원 모집 게시판

- 3, 4주차 팀원/ 외부 대회, 프로젝트 참여 팀원 모집 홍보 게시판
- 게시글에 태그를 달 수 있어요
- 마음에 드는 게시판은 스크랩할 수 있어요.

### 3번탭: 몰작교

- Gemini가 내 tech stack, MBTI, 자기소개를 기반으로 최적의 팀원 매칭
- Swipe Right!

### 4번탭:

- 내 정보 보기/수정
- 내가 저장한 팀원
- 같은 팀 되지 않기 목록
- 내가 스크랩한 글 리스트

<aside>
💡 주의: 해당 프로젝트는 완성되지 않았습니다.

</aside>

# Tech Stack

---

## Frontend
- Kotlin
- View XML

## Backend
- django
- Rest Framework

## Database
-MySQL

## Environment

백엔드 가상환경 
- Docker

데이터베이스 및 서버 환경 
- AWS + tmux

## API

로그인 - 카카오톡

팀 매칭, 프로젝트 주제 선정 - Google Gemini

기술 스택 자동인식 - Github

# Details

---

## ER Diagram
![Untitled](https://github.com/mummy-alive/Week2_front/assets/113423544/4a41f9ba-3f00-4683-8b67-4e1fcc63697e)


---

## 프론트엔드

### 1번탭: 홈 화면

- `RecyclerView` 로 홍보게시판, 프로필 미리보기 표시

### 2번탭: 팀원 모집 게시판

- `RecyclerView` 로 홍보게시판 구현.

### 3번탭: 몰작교

- `CardStackView` 로 오른쪽 Swipe 시 백엔드의 `UserLike` 에게로  `POST` 가 트리거 됨

### 4번탭: 내 정보

- 내가 like한 사용자, 같은 팀 되지 않기 한 사용자, 스크랩한 글 누르면 해당 내역을 Recyclerview로 조회 가능.

---

## 백엔드

### 온보딩

- 카카오톡 API로 유저 ID및 이름을 Token으로부터 받아 회원가입
- 온보딩 (이메일, 분반, 기술스택, 자기소개 입력) 후 로그인 및 서비스 이용 가능
- `User` 와 `Profile` 을 나누어 저장하고, Profile이 user id를 외래키로 참조하도록 하여 `User` 데이터를 최소 크기로 유지
- `Profile`

### 1번탭: 홈 화면

- `Post`를 `DATETIME` 기준으로 내림차순으로 sort하여 최신 게시물 4개를 리턴
- Gemini가 고른 최적의 팀원을 적절도를 기준으로 내림차순 sort하여 팀원 프로필 4개 리턴

### 2번탭: 팀원 모집 게시판

- `Post`를 `DATETIME` 기준으로 내림차순으로 sort하여 최신 게시물 4개를 리턴
- 상세 페이지 들어가면 `PostView` 를 보내 상세 내역을 조회할 수 있도록 구현

### 3번탭: 몰작교

- Gemini가 내 tech stack, MBTI, 자기소개를 기반으로 최적의 팀원 프로필을 선정하여 `JSON` 형식의 리스트로 반환.
- Gemini에서 잘못된 형식 리턴 시 5번 더 시도 후 에러 리턴 (Gemini API 한계도달 방지용)
- Gemini에게 보내는 프로필은 UserLike, UserBlock을 필터링한 것.
- Swipe Right 후 사용자로부터 `UserLike` row가 리턴되면 데이터베이스에 저장
- 사용자가 ‘같은 팀 하지 않기’ 를 누르면 `UserBlock` row가 리턴되며 데이터베이스에 저장

### 4번탭: 내정보

- 내 정보 보기
- 내가 저장한 팀원
- 같은 팀 되지 않기 목록
- 내가 스크랩한 글 리스트

# Milestone

---

### 1. 프론트엔드와 백엔드의 Sync

- 현재 Token 관련 sync 문제로 프론트엔드와 백엔드 간의 소통이 원활하지 않음.
- 이 부분을 해결하면 모든 기능을 연결하여 서비스를 완성할 수 있을 것으로 보임.

### 2. Github 기술 스택 인식

- 불안정한 크롤링 대신, Github 토큰으로 사용자 Github repository에 접근허가를 받는다.
- Github에서 출력되는 사용자의 언어 사용 내역을 `Set`  에 저장하여 백엔드에 리턴

### 3. 쪽지 기능

- SendBird API를 받아 실시간 채팅을 구현

# 후기

---

**조민주 (백엔드, 데이터베이스, 서버구축)**

- 백엔드와 서버 간의 연결에 대한 이해가 부족했던 것 같아 아쉽다.
- MySQL과 AWS를 평생 저주할 것
- 다양한 API를 사용해볼 수 있었다.

![Screenshot 2024-07-10 192250](https://github.com/mummy-alive/Week2_front/assets/113423544/7eca3a19-759e-424c-b294-25767247c570)


송종찬 (프론트엔드)

- 프로젝트를 구현하기 전에 확실한 설계의 필요성을 느꼈고, 규모를 현실적으로 구상해야함을 깨달았다.
- 백엔드와 통신해야할 것이 무엇인지, 얼마나 많은 것을 주고받아야 하는지 알 수 있었다.
- 토큰, 권한 그게 뭔데
