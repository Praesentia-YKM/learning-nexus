package com.tdd.spring;

import com.tdd.learn.LearnApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * @SpringBootTest는 @SpringBootApplication가 붙은 클래스를 찾아서 컨텍스트 로드를 한다.
 * 하지만 src/main 디렉토리와 src/test 의 패키지가 서로 다르기 때문에 경로를 찾지 못하는 경우가 있다.
 * 이럴 땐 @SpringBootTest(classes = LearnApplication.class)와 같이 명시해주자
 */
@SpringBootTest(classes = LearnApplication.class)
@ContextConfiguration(classes = LearnApplication.class)
class LearnApplicationTests {

	@Test
	void contextLoads() {
	}

}
