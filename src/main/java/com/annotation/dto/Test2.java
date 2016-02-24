package com.annotation.dto;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test2 {

	public static void main(String[] args) {
		Filter f1=new Filter();
		f1.setId(10);//根据id查询用户
		
		Filter f2=new Filter();
		f2.setUserName("lucy");//根据名字查询用户
		f2.setAge(18);
		
		
		Filter f3=new Filter();
		f3.setEmail("xiaoming@sina.com");//查询其中一个邮箱的用户
		
		String sql1=query(f1);
		String sql2=query(f2);
		String sql3=query(f3);
		
		System.out.println(sql1);
		System.out.println(sql2);
		System.out.println(sql3);
		
		Filter2 filter2=new Filter2();
		filter2.setId(8);
		filter2.setName("技术部");
		filter2.setAmount(20);
		System.out.println(query(filter2));
	}

	private static String query(Object filter){
		StringBuilder sb=new StringBuilder();
		//1.获取class
		Class c=filter.getClass();
		//2.获取table的名字
		boolean tableExists=c.isAnnotationPresent(Table.class);
		if(!tableExists){
			return null;
		}
		Table table=(Table) c.getAnnotation(Table.class);
		String tableName=table.value();
		sb.append("select * from ").append(tableName).append(" where 1=1");
		//3.遍历所有字段
		Field[] fieldArray=c.getDeclaredFields();
		for (Field field : fieldArray) {
			//4.处理SQL对应的字段
			//4.1获取字段名
			boolean columnExists=field.isAnnotationPresent(Column.class);
			if(!columnExists){
				continue;
			}
			Column column=(Column) field.getAnnotation(Column.class);
			String columnName=column.value();
			//4.2获取字段的值
			String fieldName=field.getName();
			/** 通过get方法获得值*/
			String getMethodName="get"+fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
			Object fieldValue=null;
			try {
				Method getMethod=c.getMethod(getMethodName);
				fieldValue= getMethod.invoke(filter);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//4.3拼装SQL
			if(fieldValue == null || (fieldValue instanceof Integer && (Integer)fieldValue==0)){
				continue;
			}
			sb.append(" and ").append(fieldName);
			if(fieldValue instanceof String){
				if(((String) fieldValue).contains(",")){
					String[] splitValues=((String) fieldValue).split(",");
					sb.append(" in(");
					for (String sv : splitValues) {
						sb.append("'").append(sv).append("'").append(",");
					}
					sb.deleteCharAt(sb.length()-1);//去掉最后的逗号
					sb.append(")");
				}else{
					sb.append("=").append("'").append(fieldValue).append("'");
				}
			}else if(fieldValue instanceof Integer){
				sb.append("=").append(fieldValue);
			}
		}
		
		return sb.toString();
	}
}
