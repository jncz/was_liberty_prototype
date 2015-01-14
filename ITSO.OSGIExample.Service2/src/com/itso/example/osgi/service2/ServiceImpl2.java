package com.itso.example.osgi.service2;

import java.util.Date;

import com.itso.example.osgi.api.HelloAPI;

public class ServiceImpl2 implements HelloAPI{

	@Override
	public void sayHello() {
		System.out.println("service 2 hello");
	}

	@Override
	public String getMsg() {
		return "Service 2: "+new Date().toString();
	}

}
