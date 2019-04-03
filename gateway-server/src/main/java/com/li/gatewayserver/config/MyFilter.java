package com.li.gatewayserver.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Classname RequestFilter
 * @Description 全局过滤器
 * @Author 李号东 lihaodongmail@163.com
 * @Date 2019-04-02 22:50
 * @Version 1.0
 */
@Component
public class MyFilter implements GlobalFilter, Ordered {

    //执行逻辑
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String uri = request.getURI().toString();
        //打印每次请求的url
        System.out.println(" uri : " + uri);
        return chain.filter(exchange);
    }

    //执行顺序
    @Override
    public int getOrder() {
        return 1;
    }
}
