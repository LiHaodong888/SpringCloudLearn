package com.li.gatewayserver.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * @Classname RateLimiterConfig
 * @Description 限流方法
 * @Author 李号东 lihaodongmail@163.com
 * @Date 2019-04-02 23:09
 * @Version 1.0
 */
@Configuration
public class RateLimiterConfig {
    @Bean(value = "remoteAddrKeyResolver")
    public KeyResolver remoteAddrKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
    }
}
