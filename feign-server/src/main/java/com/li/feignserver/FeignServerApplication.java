package com.li.feignserver;

import com.li.feignserver.server.EurekaCclientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableFeignClients // 开启Feign功能
@EnableEurekaClient
@SpringBootApplication
public class FeignServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignServerApplication.class, args);
    }


    @Autowired
    private EurekaCclientService eurekaCclientService;

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name") String name) {
        return eurekaCclientService.hello(name);
    }

}
