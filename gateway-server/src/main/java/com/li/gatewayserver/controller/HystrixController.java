package com.li.gatewayserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname HystrixController
 * @Description 熔断调用地址
 * @Author 李号东 lihaodongmail@163.com
 * @Date 2019-04-02 23:05
 * @Version 1.0
 */
@RestController
public class HystrixController {

    @RequestMapping("/defaultfallback")
    public Map<String,String> defaultfallback(){
        System.out.println("降级操作...");
        Map<String,String> map = new HashMap<>();
        map.put("resultCode","fail");
        map.put("resultMessage","服务异常");
        map.put("resultObj","null");
        return map;
    }
}
