package com.annotation.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class ReflectAnn {

	public static void main(String[] args) {
		//1.使用类加载器加载类
		try {
			Class c=Class.forName("com.annotation.test.Child");
			
			//2.找到类上面的注解
			boolean isExist=c.isAnnotationPresent(Description.class);
			if(isExist){
				//3.拿到注解实例
				Description d=(Description) c.getAnnotation(Description.class);
				System.out.println(d.desc());
			}
			//4.找到方法上的注解
			Method[] m=c.getMethods();
			for (Method method : m) {
				boolean isMExist=method.isAnnotationPresent(Description.class);
				if(isMExist){
					Description d=method.getAnnotation(Description.class);
					System.out.println(d.desc());
				}
			}
			
			//5.另一种解析方式
			for (Method method : m) {
				Annotation[] as=method.getAnnotations();
				for (Annotation annotation : as) {
					if(annotation instanceof Description){
						Description d=(Description) annotation;
						System.out.println(d.desc());
					}
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
}
