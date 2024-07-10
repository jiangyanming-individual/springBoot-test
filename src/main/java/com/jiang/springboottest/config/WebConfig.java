package com.jiang.springboottest.config;


import com.jiang.springboottest.interceptor.MyInterceptor;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;


/**
 * 将拦截器注册到WebMVC 中
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private MyInterceptor myInterceptor;

//    @Resource
//    private MyFilter myFilter;

//
//    @Resource
//    private MyListener myListener;

    /**
     * 自定义拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor)
                .addPathPatterns("/**") //拦截所有路径/api
                .excludePathPatterns("/api/login/user","/api/secure/*"); //排除登录请求
    }

    /**
     * 自定义过滤器
     *
     * @return
     */
//    @Bean
//    public FilterRegistrationBean filterRegistrationBean() {
//        FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>();
//        filter.setFilter(myFilter);
//        filter.addUrlPatterns("/*");
//        return filter;
//    }

    /**
     * 自定义监听器：
     *
     * @return
     */
//    @Bean
//    public ServletListenerRegistrationBean servletListenerRegistrationBean() {
//
//        ServletListenerRegistrationBean<EventListener> listenerRegistrationBean = new ServletListenerRegistrationBean<>();
//        listenerRegistrationBean.setListener(myListener);
//        return listenerRegistrationBean;
//    }

}
