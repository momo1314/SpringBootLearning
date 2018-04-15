package org.test.demo.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.test.demo.core.interceptor.BaseInterceptor;

@Configuration
//@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {
    /**
     * 配置自定义静态文件夹的资源访问
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //registry.addResourceHandler("/my/**").addResourceLocations("file:E:/my/");
        registry.addResourceHandler("/my/**").addResourceLocations("classpath:/my/");
        super.addResourceHandlers(registry);
    }

    /**
     * 自定义页面跳转,并且不会覆盖原有的"/"->index.html 映射
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/test").setViewName("test");
        super.addViewControllers(registry);
    }

    /**
     * 添加拦截器,除了rcvludePathPatterns中的之外,其他全都要经过其拦截检查
     * 需要写一个Interceptors的类
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new BaseInterceptor()).addPathPatterns("/**").excludePathPatterns("/login","/register","/hello");
        super.addInterceptors(registry);
    }
}
