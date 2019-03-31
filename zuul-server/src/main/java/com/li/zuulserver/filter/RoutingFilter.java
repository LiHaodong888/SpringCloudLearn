package com.li.zuulserver.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Classname MyFilter
 * @Description TODO
 * @Author 李号东 lihaodongmail@163.com
 * @Date 2019-03-31 08:20
 * @Version 1.0
 */

@Component // 注入bean
public class RoutingFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(RoutingFilter.class);


    /**
     * 过滤器的类型
     * pre：可以在请求被路由之前调用。我们可利用这种过滤器实现身份验证、在集群中选择请求的微服务、记录调试信息等。
     * roting：在路由请求时候被调用。这种过滤器用于构建发送给微服务的请求，并使用Apache HttpClient或Netfilx Ribbon请求微服务。
     * post：在routing和error过滤器之后被调用。这种过滤器可用来为响应添加标准的HTTP Header、收集统计信息和指标、将响应从微服务发送给客户端等。
     * error：处理请求时发生错误时被调用
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤的顺序 越小越先很执行
     *
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否过滤 true 过滤 false 不执行过滤器
     *
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑
     *
     * @return
     * @throws ZuulException
     */

    @Override
    public Object run() throws ZuulException {
        // 获取请求的上下文类 注意是：com.netflix.zuul.context包下的
        RequestContext ctx = RequestContext.getCurrentContext();
        //避免中文乱码
        ctx.addZuulResponseHeader("Content-type", "text/json;charset=UTF-8");
        ctx.getResponse().setCharacterEncoding("UTF-8");
        //获取request对象
        HttpServletRequest request = ctx.getRequest();
        log.info("请求方式：{},地址：{}", request.getMethod(), request.getRequestURL().toString());
        // 获取token参数
        Object accessToken = request.getParameter("token");
        if (accessToken == null) {
            // 使其不进行转发 自定义route类型时，在shouldFilter中也需要进行此参数判断
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            try {
                ctx.getResponse().getWriter().write("非法访问");
            } catch (Exception e) {
            }
            return null;
        } else {
            if (!"123".equals(accessToken)) {
                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(HttpStatus.SERVICE_UNAVAILABLE.value());
                try {
                    ctx.getResponse().getWriter().write("token is error");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        return null;
    }
}
