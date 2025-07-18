# spring-gift-enhancement

### Step1 [ 07/15 ]
- [x] JdbcTemplate -> JPA로 Refactoring
  - [x] Member 관련 기능 JPA화
  - [x] Product 관련 기능 JPA화
  - [x] Wishlist 관련 기능 JPA화
- [x] DataJpaTest를 통한 TestCode 작성

### Step2 [ 07/16 ]
- [x] 상품 목록 Pagination 적용
- [x] 위시리스트 Pagination 적용
- [x] Step1 코드 리뷰 수정
  - [x] 회원가입시, Member객체 생성시 id없는 생성자 만들기
  - [x] IllegalArgumentException -> EntityNotFoundException 으로 변경
  - [x] JpaTest AssertAll() 적용
  - [x] 사용하지 않는 반환값 void로 변경
  - [x] ProductService setter 대신 다른 method 사용
  - [x] Jpa Test에서 DataIntegrityViolationException 발생 테스트

### Step3 [ 07/18 ]
<details>
<summary>이전 Step에서 완료못한 코드 처리</summary>
<div markdown="1">

- [x] Google Style 적용 및 미사용 import문 최적화
- [x] @PageableDefault 공통 적용
- [x] Update Method에 EntityNotFoundException 적용
- [x] TestCode 중복 체크 제거
- [x] 도메인 객체 테스트 코드 작성
- [ ] Application 실행 안해도 테스트 수행 가능하도록 수정
</div>
</details>
