package com.jiang.springboottest.filter;

import com.auth0.jwt.interfaces.Claim;
import com.jiang.springboottest.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * JWT过滤器，拦截 /secure的请求
 */
@Slf4j
@Order(1)
@WebFilter(filterName = "JwtFilter", urlPatterns = "/api/secure/**")
public class JwtFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;

        System.out.println("开始执行jwtFilter");
        long startTime = System.currentTimeMillis();
        response.setCharacterEncoding("UTF-8");
        //获取 header里的token
        final String token = request.getHeader("token");
        System.out.println("过滤器获得的token:" + token);

        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(request, response);
        }
        // Except OPTIONS, other request should be checked by JWT
        else {
            if (token == null) {
                response.getWriter().write("没有token！");
                return;
            }
            Map<String, Claim> userData = JwtUtils.verifyToken(token);
            if (userData == null) {
                response.getWriter().write("token不合法！");
                return;
            }
            Integer id = userData.get("id").asInt();
            String userName = userData.get("userName").asString();
            String password = userData.get("password").asString();
            //拦截器 拿到用户信息，放到request中
            request.setAttribute("id", id);
            request.setAttribute("userName", userName);
            request.setAttribute("password", password);
            chain.doFilter(request, res);
            System.out.println("执行jwtFilter完毕,耗时：" + (System.currentTimeMillis() - startTime));
        }
    }

    @Override
    public void destroy() {
    }
}