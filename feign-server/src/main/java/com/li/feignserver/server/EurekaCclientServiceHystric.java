package com.li.feignserver.server;

import org.springframework.stereotype.Component;

/**
 * @Classname EurekaCclientServiceHystric
 * @Description 回调异常
 * @Author 李号东 lihaodongmail@163.com
 * @Date 2019-03-28 22:58
 * @Version 1.0
 */
@Component
public class EurekaCclientServiceHystric implements EurekaCclientService{

    @Override
    public String hello(String name) {
        return "调用eureka-client服务异常:调用"+name;
    }
}
