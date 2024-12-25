package com.shen.config;

import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 映射/login请求到login视图
//        registry.addViewController("/login").setViewName("/admin/login");
        // 映射/index请求到user/index视图
//        registry.addViewController("/admin").setViewName("admin/login");
        registry.addViewController("/").setViewName("user/header");
    }
@Override
public void addInterceptors(InterceptorRegistry registry) {
    // 注册拦截器，并指定拦截路径
    registry.addInterceptor(new LoginHandlerInterceptor())
            .addPathPatterns("/**")   // 拦截/**路径
            .excludePathPatterns("/admin/login")
            .excludePathPatterns("/user/login")
            .excludePathPatterns("/user/register")
            .excludePathPatterns("/validateCode") //不拦截生成验证码
            .excludePathPatterns("/css/**") // 不拦截所有css文件
            .excludePathPatterns("/js/**")  // 可选：不拦截js文件
            .excludePathPatterns("/images/**"); // 可选：不拦截图片文件
}
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // 映射 /static/** 到 classpath:/static/
    registry.addResourceHandler("/static/**")
            .addResourceLocations("classpath:/static/");

    // 如果你想要特定的映射，比如 /css/** 到 classpath:/static/css/
    registry.addResourceHandler("/css/**")
            .addResourceLocations("classpath:/static/css/");
    // 映射 /js/** 到 classpath:/static/js/
    registry.addResourceHandler("/js/**")
            .addResourceLocations("classpath:/static/js/");
}
    @Bean
    public PaginationInnerInterceptor paginationInterceptor() {
        return new PaginationInnerInterceptor();
    }
}
