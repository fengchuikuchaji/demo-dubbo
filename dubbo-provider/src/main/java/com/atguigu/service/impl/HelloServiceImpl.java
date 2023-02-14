package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.service.HelloService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 包名:com.atguigu.service.impl
 * @author Leevi
 * 日期2023-02-05  14:08
 * 声明式事务导致提供者无法注册到注册中心?
 * 1. 如果使用声明式事务，底层会用到JDK的动态代理，导致创建出来的代理对象的类型变成:com.sun.proxy.$Proxy18
 * 2. 而我们需要注册到注册中心的服务提供者应该是com.atguigu.service.HelloService
 *
 * 解决方案: 不让声明式事务使用JDK的动态代理,而让它使用CGLIB的动态代理
 * 1. JDK的动态代理底层是创建接口的新实现类，然后再创建新实现类的对象,所以spring底层但凡发现被代理类实现类接口，它默认就使用JDK的动态代理
 * 2. CGLIB的动态代理底层是使用织入的方式创建代理对象(其实就是创建被代理类的子类，然后创建子类对象)
 *   2.1 编译前织入: 一般是针对我们自己写的类，能拿到编译前的文件
 *   2.2 编译后织入: 一般是针对第三方jar包中的类
 */
@Service(interfaceClass = HelloService.class)
public class HelloServiceImpl implements HelloService {

    @Transactional
    @Override
    public String sayHello(String name) {
        return "[20881]hello:"+name;
    }
}
