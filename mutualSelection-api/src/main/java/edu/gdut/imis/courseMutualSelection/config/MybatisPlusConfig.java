package edu.gdut.imis.courseMutualSelection.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author miemiehoho
 * @date 2021/11/18 21:49
 */
@Configuration // 标示，其实就是@Component注解，让spirng可以扫描到它
@MapperScan("edu.gdut.imis.courseMutualSelection.dao.mapper") // 接口扫包路径，扫包，并生成代理实现类注入到spring容器
public class MybatisPlusConfig {

    /**
     * 拦截器，分页插件
     *
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());// 分页插件
        return interceptor;
    }
}
