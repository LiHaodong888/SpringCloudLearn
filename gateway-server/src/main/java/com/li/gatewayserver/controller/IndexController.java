package com.li.gatewayserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname IndexController
 * @Description TODO
 * @Author 李号东 lihaodongmail@163.com
 * @Date 2019-04-02 23:11
 * @Version 1.0
 */
@RestController
public class IndexController {

    @RequestMapping("/hello")
    public String hello(String name){
        return "hi " + name;
    }

}
