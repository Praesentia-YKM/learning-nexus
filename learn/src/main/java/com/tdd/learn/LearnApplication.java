package com.tdd.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBootApplication 은 자동 컴포넌트 스캔 등이 이루어지는데 나 자신이 속한 패키지 또는 하위 패키지가 스캔대상이다.
 */
@SpringBootApplication
public class LearnApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnApplication.class, args);
	}

}
