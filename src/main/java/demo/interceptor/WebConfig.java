package demo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;

/**
 * Author QAQCoder , Email:QAQCoder@qq.com
 * Create time 2019/10/28 15:01
 * Class description：
 */
@Configuration
//@EnableWebMvc 去掉这个注解，不然静态资源无法访问
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private SessionInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // {/**}表示拦截所有请求路径，并由SessionInterceptor处理
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/**");
    }
}