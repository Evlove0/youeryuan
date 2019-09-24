package com.isoft.youeryuan;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Authof: ZhangYingHao
 * @Date: Create in 16:32 2018/8/8
 */
@Configuration
public class MyWebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/Photo/**").addResourceLocations("file:D:/study/企业项目/前端/Youeryuan/assets/activePhoto/");
    }
}
