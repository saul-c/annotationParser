package com.annotation.test;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 * 
 * 1，@Target---作用域（constructor(构造方法声明)，field（字段声明），local_variable（局部变量声明），method（方法声明），package（包声明），parameter（参数声明），type（类，接口声明））
 * 2，@Retention---生命周期（source：只在源码显示，编译时会丢弃。class：编译时会记录到class中，运行时忽略。runtime：运行时存在，可以通过反射读取）
 * 3，Inherited---标识注解(允许子类继承)
 * 4，Documented---生成Javadoc
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Description {

	/**成员要以无参和无异常的方式声明*/
	String desc();
	
	/**支持基本数据类型，string，不支持map list ...*/
	String author();
	
	/** 可以定义默认值*/
	int age() default 18;
}
