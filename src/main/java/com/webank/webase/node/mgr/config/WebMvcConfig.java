package com.webank.webase.node.mgr.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * web configuration.
 *
 */
@Data
@Configuration
//public class WebMvcConfig implements WebMvcConfigurer {
public class WebMvcConfig {

    @Value("${server.port}")
    private int port;

//    @Autowired
//    private AppIntegrationFilter appIntegrationFilter;
//    @Autowired
//    private ConstantProperties constants;
//
//    @Bean
//    public AccountFilter setAccountFilter() {
//        return new AccountFilter();
//    }
//
//    /**
//     * 注册拦截器
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(appIntegrationFilter).addPathPatterns("/api/**");// 自定义拦截的url路径
//        registry.addInterceptor(setAccountFilter()).addPathPatterns("/**")
//                .excludePathPatterns(constants.getPermitUrlArray());
//    }
//
//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//        argumentResolvers.add(currentAccountMethodArgumentResolver());
//    }
//
//    @Bean
//    public CurrentAccountMethodArgumentResolver currentAccountMethodArgumentResolver() {
//        return new CurrentAccountMethodArgumentResolver();
//    }
}
