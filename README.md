# 환율 실시간 계산 프로젝트
>## _Tech Stack_
>>### Spring Boot (2.6.6)
>>### Spring Web Flux (API 호출 전용)
>>### Spring MVC
>>### Ajax
>>### Lombok
>>### Mock
>>### OpenAPI 3.0
---
## _요구사항_
- 송금 국가는 미국으로 고정입니다. 통화는 미국달러(USD)입니다.
- 수취국가는 한국, 일본, 필리핀 세 군데 중 하나를 select box로 선택합니다. 각각 통화는 KRW, JPY, PHP 입니다.
- 수취국가를 선택하면 아래 환율이 바뀌어나타나야 합니다. 환율은 1 USD 기준으로 각각 KRW, JPY, PHP의 대응 금액입니다.
- 송금액을 USD로 입력하고 Submit을 누르면 아래 다음과 같이 수취금액이 KRW, JPY, PHP 중 하나로 계산되어서 나와야 합니다.
- 환율과 수취금액은 소숫점 2째자리까지, 3자리 이상 되면 콤마를 가운데 찍어 보여줍니다. 예를 들어 1234라면 1,234.00으로 나타냅니다.
- 환율정보는 https://currencylayer.com/ 의 무료 서비스를 이용해서 실시간으로 가져와야 합니다. 웹 서버가 시작될 때 한번 가져와서 계속 사용해도 되고, 매번 새로운 환율 정보를 가져와도 됩니다.
- 새로운 무료 계정을 등록해서 API 키를 받아서 사용하면 됩니다. 샘플로 등록된 계정의 키를 예를 들면 다음과 같습니다. http://www.apilayer.net/api/live?access_key=ee50cd7cc73c9b7a7bb3d9617cfb6b9c
- 환율을 미리 계산해서 html/javascript 안에 넣어두고 수취국가를 변경할 때마다 이를 자바스크립트로 바로 가져와서 보여줘도 좋고,
- 혹은 매번 수취국가를 선택/변경할 때마다 API로 서버에 요청을 해서 환율정보를 가져오게 해도 좋습니다.
- Submit을 누르면 선택된 수취국가와 그 환율, 그리고 송금액을 가지고 수취금액을 계산해서 하단에 보여주면 됩니다. API를 이용해서 서버에서 계산해서 뿌려도 되고 자바스크립트로 미리 가져온 환율을 계산해서 수취금액을 보여줘도 되고 Submit 버튼으로 폼을 submit해서 화면을 새로 그려도 됩니다.
- 수취금액을 입력하지 않거나, 0보다 작은 금액이거나 10,000 USD보다 큰 금액, 혹은 바른 숫자가 아니라면 “송금액이 바르지 않습니다"라는 에러 메시지를 보여줍니다. 메시지는 팝업, 혹은 하단에 빨간 글씨로 나타나면 됩니다.
- 본 테스트는 지원자의 스프링 숙련도를 보기 위한 테스트이며 핵심 기능(외부 API 처리, 에러핸들링등)을 스프링으로 구현하셔야 합니다.
- API 설계 및 구현능력을 중점적으로 봅니다.
- 사용기술은 스프링 4.0이후 버전을 사용하면 어떤 기술을 이용해도 상관없습니다. 스프링 부트를 이용해도 됩니다.
- 클라이언트 화면은 스프링의 뷰 기술(jsp, thymeleaf 등)을 이용해도 좋고, React나 Angular 등을 이용해도 좋습니다.
---
## _시연화면_
<img width="643" alt="스크린샷 2022-04-17 21 53 44" src="https://user-images.githubusercontent.com/72784474/163715210-2fe536f5-f892-4b3a-83e0-0140c85d84b9.png">

### _연속적이고, 빠른 API 호출 테스트를 위해 Radio 박스로 구현했습니다._
---

<img width="643" alt="image" src="https://user-images.githubusercontent.com/72784474/163715810-33ab47a6-99b3-4151-b3d5-e19c2f9de7e5.png">

### _비정상적인(너무 빠른) 호출 시, 500 에러를 반환하여 계산 기능을 Disabled 시켰습니다._
---

<img width="643" alt="image" src="https://user-images.githubusercontent.com/72784474/163715884-82636eab-2d7a-4bcd-b49e-1225844492dd.png">


### _0~10000 사이의 금액이 아닐 시, 계산 기능을 Disalbed 시켰습니다._
---

<img width="643" alt="image" src="https://user-images.githubusercontent.com/72784474/163715939-56e2dd83-e92d-44de-9d1a-40d1140a449d.png">

### _서비스에서 지원하지 않는 국가 선택 시, 계산 기능을 Disabled 시켰습니다._
---

<img width="643" alt="image" src="https://user-images.githubusercontent.com/72784474/163716633-f5f6afcb-8210-4cdb-83df-59ca2ad4f976.png">

### _숫자 외 다른 값 입력 시, 송금액이 올바르지 않음을 출력했습니다._

---

<img width="1415" alt="image" src="https://user-images.githubusercontent.com/72784474/163717390-832dd4d8-420c-40c3-9fee-b45fa37aa84f.png">

### _/transfer/quotes (GET) OpenAPI 3.0 문서 예시입니다._

---

## _회고_
#### 프로젝트를 진행하면서, 그동안 막혔던 체증이 내려가는 느낌이였습니다. 
#### "내가 배운것을 도대체 언제 써먹지?"라는 불안감과, 그 불안감을 해소하기 위해 강의, 교육자료만 주구장창 보았습니다.
#### 예상대로, 프로젝트를 시작할 때, 머릿속에 도무지 정리가 되지 않았습니다.
#### 하지만, 어떤 기능을 구현시킴에 있어, 필요한 기능들을 알아보았고, 다시 강의자료를 살피며 그동안 학습했던 코드를 리뷰하였습니다.
#### 놀랍게도, 배웠던 지식들이 다시 어렴풋이 떠올랐습니다. 그동안 배운 것들을 좀 더 정확하게 개념을 정리할 수 있었습니다.
#### 해당 프로젝트는, 제가 강의를 듣고 처음으로 기술을 "제대로" 적용한 첫 프로젝트이며, 앞으로의 회고에서도 오래 기억될 것 같습니다.

---

## _아쉬운 점_
- 에러 핸들링을 Controller 부분이나, Service에서 Exception을 세세하게 나누어, 조금 더 정확하게 오류를 나누지 못한 점
- 지원하는 송금 국가는(KRW, JPY, PHP) 잘 받아왔지만, 이를 Thymeleaf에서 동적으로 구현하지 못한 점
- 에러 메시지를 메세지 국제화하지 못한 점
- Etag를 외부 API에서 받아오지 못한 점(API 서버 측 문제일 수 있으나, 여러번 시도해도 받아오지 못했습니다.)
- Etag를 외부 API <-> 서버가 아닌, 서버 <-> 클라이언트 측면에서 비교하여 비교적 성공적이지 못한 API 최적화를 한 점
- 테스트 케이스를 여러가지 방면으로 (Controller 테스트 등) 하지 못한 점
- 기타 여러가지 에러 핸들링에 대해 완벽하지 못한 점

## _아쉬운점은 꾸준히 개선하여, 마음에 드는 서비스를 만들고 싶습니다._





