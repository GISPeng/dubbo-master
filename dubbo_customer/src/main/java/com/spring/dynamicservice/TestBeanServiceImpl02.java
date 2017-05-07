package com.spring.dynamicservice;

import org.springframework.stereotype.Service;

@Service
public class TestBeanServiceImpl02 implements TestBeanService{

	public String printMsg() {
		System.out.println("TestBeanServiceImpl02");
		return "TestBeanServiceImpl02";
	}

}
