# MySelectShop
![Generic badge](https://img.shields.io/badge/SpringBoot-5.0.7-yellowgreen.svg) ![Generic badge](https://img.shields.io/badge/NaverAPI-green.svg)

## 관심상품 조회 기능 구현
![image](https://github.com/burninghering/SpringBoot_Sparta/assets/37091602/a307fbcc-0d3b-4597-937e-6264d912e6dd)

## 관심 상품의 희망 최저가 업데이트 기능 구현
![image](https://github.com/burninghering/SpringBoot_Sparta/assets/37091602/06a3d889-5582-4cdf-b0eb-fd3df92df638)

## 관심 상품 등록 기능 구현
![image](https://github.com/burninghering/SpringBoot_Sparta/assets/37091602/4b7da6b1-0515-437f-b212-6d17aec8a15a)

### **My Selectshop**

- **프로젝트 개요:**`myselectshop`은 네이버 쇼핑 API를 활용하여 사용자가 관심 있는 상품을 조회하고 관리할 수 있는 기능을 제공하는 웹 애플리케이션입니다. 사용자가 관심 상품의 정보를 등록하고, 희망 최저가를 설정하면 API를 통해 실시간으로 정보를 업데이트할 수 있도록 설계되었습니다.

---

### **주요 기술 및 역할**

1. **네이버 쇼핑 API 연동**
    - 네이버 쇼핑 API를 사용해 상품 검색 및 데이터를 받아오는 기능 구현.
    - 검색된 상품 정보를 JSON 형식으로 받아와 파싱 후 사용자에게 제공.
    - API 호출의 인증 및 요청 처리(HTTP Header 설정, Access Token 관리)를 구현.
2. **관심 상품 조회 및 등록 기능**
    - 사용자 입력에 따라 관심 상품을 등록하고 데이터베이스에 저장하는 기능 구현.
    - Spring MVC 구조를 기반으로, 컨트롤러-서비스-리포지토리 간 계층적 설계를 통해 코드 유지보수성을 확보.
    - 등록된 관심 상품 목록을 조회하고, 페이징 처리를 통해 많은 데이터를 효율적으로 표시.
3. **희망 최저가 업데이트 기능**
    - 사용자가 등록한 관심 상품의 희망 최저가를 설정 및 업데이트할 수 있는 기능 구현.
    - 가격 비교 로직을 적용하여 API를 통해 현재 상품 가격을 조회하고, 희망 가격 이하로 떨어지면 알림 제공(추후 확장 가능하도록 설계).
4. **백엔드 로직 설계**
    - 상품 정보 및 사용자의 관심 데이터를 관리하기 위한 MySQL 데이터베이스 설계 및 연동.
    - JPA를 활용하여 데이터베이스 연산을 간결하게 처리.
    - 예외 처리 로직을 통해 API 호출 실패 또는 데이터 누락 상황에 대비.

---

### **성과 및 기여**

- **API 활용 능력:**
    
    네이버 쇼핑 API를 활용하여 상품 관리와 사용자 맞춤형 기능 제공.
    
- **사용자 경험 개선:**
    
    관심 상품의 등록 및 희망 최저가 기능을 통해 사용자 편의성을 높임.
    
- **확장 가능성:**
    
    관심 상품 데이터와 가격 비교 로직을 모듈화하여 향후 알림 시스템과 같은 추가 기능 확장이 용이하도록 설계.
    

---

### **기술 스택**

- **프로그래밍 언어:** Java
- **프레임워크:** Spring Boot, Spring MVC
- **데이터베이스:** MySQL
- **API 연동:** 네이버 쇼핑 API
- **기타:** JPA, JSON
