package com.annotation.test;

public interface Person {

	public String name();
	
	public int age();
	
	/**@deprecated 表示过时的方法*/
	@Deprecated
	public void sing();
}
