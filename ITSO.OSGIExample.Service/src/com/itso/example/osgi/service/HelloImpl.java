package com.itso.example.osgi.service;

import java.util.Date;

import com.itso.example.osgi.api.HelloAPI;

public class HelloImpl implements HelloAPI {

	@Override
	public void sayHello() {
		System.out.println("hell");
	}

	@Override
	public String getMsg() {
		return new Date().toString();
	}

}
