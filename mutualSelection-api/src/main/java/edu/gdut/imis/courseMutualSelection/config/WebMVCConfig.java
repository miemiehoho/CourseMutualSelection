package edu.gdut.imis.courseMutualSelection.config;

import edu.gdut.imis.courseMutualSelection.handler.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author miemiehoho
 * @date 2021/11/18 22:04
 */
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    /**
     * 跨域配置
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 定义所有接口，允许域名：http:localhost:8080 访问
        registry.addMapping("/**").allowedOrigins("http:localhost:8080");
    }

    /**
     * 配置登录拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 先拦截test，以后有需要再改成需要拦截的接口
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/user/**")
                .addPathPatterns("/usertopic/**")
                .addPathPatterns("/class")
                .excludePathPatterns("/login")
                .excludePathPatterns("/swagger-resources");
    }
}
