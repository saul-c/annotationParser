package com.annotation.test;

@Description(desc="这是类注解",author="ashuo",age=23)
public class Child implements Person{

	@Override
	@Description(desc="这是方法注解",author="ashuo",age=23)
	public String name() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int age() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void sing() {
		// TODO Auto-generated method stub
		
	}

}
