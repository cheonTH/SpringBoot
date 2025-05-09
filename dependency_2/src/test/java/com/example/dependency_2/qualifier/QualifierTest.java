package com.example.dependency_2.qualifier;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j 
public class QualifierTest {
	
	//컴퓨터타입의 객체를 주입받으려고 함
	//Laptop도 있고 Desktop도 있음
	//스프링 입장에서는 개발자가 어떤걸 원하는지 알 수 없음
	@Autowired
	@Qualifier("laptop")
	Computer laptop;
	
	@Autowired
	@Qualifier("desktop")
	Computer desktop;
	
	//호출할 때 마다 @Qualifier 붙이기 귀찮을 때
	//호출 될 곳을 @Primary로 지정
	@Autowired
	Computer computer; //laptop의 값이 들어감
	
	
	@Test
	public void computerTest() {
		log.info("모니터 너비 : {} " , laptop.getScrennWidth());
		log.info("모니터 너비 : {} " , desktop.getScrennWidth());
		log.info("모니터 너비 : {} " , computer.getScrennWidth());
	}
}
