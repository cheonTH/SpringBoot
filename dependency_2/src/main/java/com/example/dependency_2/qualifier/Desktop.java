package com.example.dependency_2.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("desktop")
public class Desktop implements Computer{
	
	@Override
	public int getScrennWidth() {
		return 1440;
	}
}
