# annotationParser
java 注解解析

# 按照运行机制分类

源码注解---注解只在源码中存在，编译成.class文件就不存在了
编译时注解---注解在源码和.class文件中都存在（如：JDK内置系统注解:@Override,@Deprecated,@Suppvisewarnings）
运行时注解---在运行阶段还起作用，甚至会影响运行逻辑的注解（如：Spring中@Autowried）

# 自定义注解语法要求
1.@interface ----表示定义的不是类，也不是接口，是定义注解的关键字
2.支持的类型---基本的数据类型，string，class，Annotation,Enumeration
3.成员变量要以无参无异常的方式声明
4.如果注解只有一个成员，则成员名必须取名为value（），可以忽略成员名和赋值号（=）。
5.注解类可以没有成员---那就是标识注解

# 解析注解
通过反射获取类，函数或成员上的运行时注解信息，从而实现动态控制程序运行的逻辑。

# 运行结果Test
select * from user where 1=1 and id=10
select * from user where 1=1 and userName='lucy'
select * from user where 1=1 and email in('xiaoming@sina.com','13812345678@163.com','23456789@qq.com')

# 运行结果Test2
select * from user where 1=1 and id=10
select * from user where 1=1 and userName='lucy' and age=18
select * from user where 1=1 and email='xiaoming@sina.com'
select * from department where 1=1 and id=8 and name='技术部' and amount=20
