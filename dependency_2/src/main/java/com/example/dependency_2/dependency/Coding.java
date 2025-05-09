package com.example.dependency_2.dependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

//코딩은 컴퓨토가ㅓ 있어야 할 수 있음
//코딩 클래스는 컴퓨터ㅗ에 의존성이 필요
@Component
@Getter
@Setter
@RequiredArgsConstructor
public class Coding {
	private final Computer computer;
	
	//객체를 메모리에 올리면서 생성자는 호출이 됨
	//이때 필요한 의존성을 매개변수에 스프링이 주입을 해줌
//	@Autowired
//	public Coding(Computer computer) {
//		this.computer = computer;
//	}
	
//	@Autowired
//	public void setComputer(Computer computer) {
//		this.computer = computer;
//	}
	
}
