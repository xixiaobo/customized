package com.swj.customized.config;

/**
 * @author : xixiaobo@gmail.com
 * @date : 2019/4/18 15:36
 * @Description:
 */

import com.swj.customized.tool.Utils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceHandler是指你想在url请求的路径
        // addResourceLocations是图片存放的真实路径
         registry.addResourceHandler("/image/**").addResourceLocations("file:"+ Utils.getpath()+"/img/");
         super.addResourceHandlers(registry);
    }
}


