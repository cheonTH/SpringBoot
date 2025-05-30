package com.example.dependency_2.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Qualifier("laptop")
@Primary
public class Laptop implements Computer{
	
	@Override
	public int getScrennWidth() {
		return 1920;
	}
}
