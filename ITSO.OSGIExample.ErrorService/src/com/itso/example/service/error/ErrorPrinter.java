package com.itso.example.service.error;

import java.util.Map;

import com.itso.example.osgi.api.HelloAPI;

public class ErrorPrinter implements HelloAPI{

	@Override
	public void sayHello() {
		System.out.println("error service");
	}

	@Override
	public String getMsg() {
		return "Service is not available now";
	}
	
	public void reg(HelloAPI api,Map props){
		System.out.println("reg");
	}
	
	public void unreg(HelloAPI api,Map props){
		System.out.println("un reg");
	}

}
