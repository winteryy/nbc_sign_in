# 내일배움캠프 앱개발 입문주차 과제
## 회원가입

### 실행 이미지
<div align="center">
<p align="center" width="100%">
    <image src = "./screenshots/sc_001.png" width="30%" >
</p>

#### SignInActivity
<br/>
<br/>
  
<p align="center" width="100%">
    <image src = "./screenshots/sc_002.png" width="30%" >
</p>

#### SignUpActivity
<br/>
<br/>
  
<p align="center" width="100%">
    <image src = "./screenshots/sc_003.png" width="30%" >
</p>

#### HomeActivity
<br/>
<br/>

<p align="center" width="100%">
    <image src = "./screenshots/sc_004.png"width="30%" >
    <image src = "./screenshots/sc_005.png"width="30%" >
</p>

#### Validation 및 `Toast` 발생
<br/>
<br/>
</div>

### 요구사항
- [x] 지정된 양식에 맞춰 UI 및 코드 구현하기
- [x] `ViewBinding`, `Fragment` 등 커리큘럼에서 다루지 않은 내용 사용하지 않기 

#### SignInActivity
- [x] 아이디, 비밀번호 EditText로 입력 받기
- [x] EditText에 플레이스 홀더 넣기
- [x] 비밀번호 EditText 마스킹 처리
- [x] 로그인 버튼 클릭시, `HomeActivity` 실행 및 `extra`로 아이디 넘기기
- [x] 아이디, 비밀번호 모두 입력해야 로그인 되도록 처리
- [x] 로그인 성공 여부 `Toast`로 출력
- [x] 회원가입 버튼 클릭시, `SignUpActivity` 실행
- [x] 회원가입 페이지에서 입력한 아이디/비밀번호가 로그인 화면으로 돌아왔을 때 자동으로 입력되도록 하기

#### SignUpActivity
- [x] 모든 항목 입력해야 회원가입 가능하도록 처리
- [x] 빈 항목 있을시, `Toast`로 출력
- [x] 비밀번호 마스킹 처리
- [x] 회원가입 버튼 클릭시, `SignInActivity`로 돌아가기(finish 활) 
용

#### HomeActivity
- [x] `SignInActivity`에서 받은 `extra data`(아이디) 화면에 표시하기
- [x] 안드로이드 `Widget` 활용해 자기소개 페이지 디자인하기 
- [x] 종료 버튼 클릭시, `SignInActivity`로 돌아가기(finish 활용) 
- [x] 자기소개 페이지가 시작될 때, 이미지 5장 중 랜덤으로 1장 출력되도록 하기

  

### What to
- 요구사항에 맞춰 UI 및 코드를 그대로 구현했습니다.


### How to
- UI는 `ConstraintLayout` 기반 xml로 작성했습니다.

- `android:hint`, `android:inputType` 등 기본 위젯의 속성들을 활용해 UI적인 요구사항을 달성했습니다.

- `registerForActivityResult()`를 통해 받은 `Activity`의 `Result`를 이용해, 회원가입 성공 시 로그인 페이지에 정보를 자동으로 기입하도록 했습니다.

- `Intent`의 `extra`를 통해 회원 정보 페이지에 로그인 정보를 전달합니다.

- `View`의 동적인 이용은 `findViewById`만 사용해 구현했습니다.

- Validation 과정은 심플하게 `Activity` 내에서 처리되며, 각 항목의 Blank 여부만 판단합니다.

### 기타
- `GuideLine` 위젯을 통해 레이아웃 배치의 기준점을 화면의 중앙으로 설정해, 어느정도 화면 비율이 달라져도 쏠리지 않은 레이아웃을 제공합니다.<br/>하지만 이미지의 크기를 고정한 탓에 `Landscape` 방향의 화면에 대해서는 대응할 수 없습니다.
