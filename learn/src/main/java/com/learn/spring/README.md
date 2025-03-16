스프링컨텍스트를 의존하는 패키지 모듈화
- @SpringBootApplication 는 기본적으로 나자신 포함 하위 패키지만을 componenet Scan 하기 때문에 추후 모킹 및 테스트 코드 작성에 유리함을 가져가기 위해 패키지 분리