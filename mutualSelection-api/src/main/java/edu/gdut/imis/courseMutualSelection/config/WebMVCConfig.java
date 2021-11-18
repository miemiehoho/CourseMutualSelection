package edu.gdut.imis.courseMutualSelection.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author miemiehoho
 * @date 2021/11/18 22:04
 */
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

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
}
