package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 包名:com.atguigu.controller
 *
 * @author Leevi
 * 日期2023-02-05  14:29
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    @Reference(loadbalance = "consistenthash")
    private HelloService helloService;

    @GetMapping("/sayHello")
    public String sayHello(@RequestParam("name") String name){
        System.out.println("hot fix热修复.......");
        System.out.println("hello master......");
        return helloService.sayHello(name);
    }
}
