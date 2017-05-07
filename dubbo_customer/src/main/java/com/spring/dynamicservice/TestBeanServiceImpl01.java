package com.spring.dynamicservice;

import org.springframework.stereotype.Service;

@Service
public class TestBeanServiceImpl01 implements TestBeanService{

	public String printMsg() {
		System.out.println("TestBeanServiceImpl01");
		return "TestBeanServiceImpl01";
	}
}
