package com.li.ribbonserver;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableHystrix // 开启Hystrix功能
@RestController
@EnableEurekaClient
@SpringBootApplication
public class RibbonServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RibbonServerApplication.class, args);
    }


    @Bean
    @LoadBalanced // 开启负载均衡
    RestTemplate restTemplate() {
        return new RestTemplate();
    }


    // 发生熔断时 会调用我们自定义的异常方法 则执行fallback
    @HystrixCommand(fallbackMethod = "helloError")
    @RequestMapping("/hello")
    public String hello(String name) {

        // 地址直接写服务名称即可
        String hello = this.restTemplate().getForObject("http://eureka-client/hello?name" + name, String.class);
        return hello;
    }

    public String helloError(String name) {
        return "服务器异常,请稍后再试";
    }

}
