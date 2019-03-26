package com.li.feignserver.server;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * eureka-client 是调用的服务名
 */
@FeignClient(value = "eureka-client")
public interface EurekaCclientService {

    @GetMapping(value = "/hello")
    String hello(@RequestParam(value = "name") String name);
}
