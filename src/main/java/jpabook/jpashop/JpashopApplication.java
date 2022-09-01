package jpabook.jpashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//스프링 부트는 이 annotation이 붙은 package, 그 하위의 모든 package의 comppnentscan 대상을 스프링빈에 등록
public class JpashopApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpashopApplication.class, args);
	}

}
