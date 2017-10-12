package com.msp.springmvc.config;

import com.msp.springmvc.Interceptor.DemoInterceptor;
import com.msp.springmvc.messageconverter.MyMessageConverter;
import org.springframework.cglib.core.Converter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;

/**
 * Created by zhao on 2017/2/10.
 *
 *  spring mvc的定制配置需要配置类继承一个WebMvcConfigurerAdapter类,并在
 *  此类使用@EnableWebMvc注解,来开启对Spring MVC的配置支持.
 */


@Configuration
@ComponentScan("com.msp.springmvc")
@EnableWebMvc//开启SpringMVC支持,若无此句,重写WebMvcConfigurerAdapter方法无效
@EnableScheduling//计划任务的支持
public class MymvcConfig extends WebMvcConfigurerAdapter {
    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/classes/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }

    /*
        静态资源映射
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceHandler指的是对外暴露的访问路径,addResourceLocations指的是文件放置的目录
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
    }


    /*
        拦截器
     */
    @Bean
    public DemoInterceptor demoInterceptor(){
        return new DemoInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(demoInterceptor());
    }

    /*
        集中跳转
     */

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/index");
        registry.addViewController("/toUpload").setViewName("/upload");
        registry.addViewController("/converter").setViewName("/converter");
        registry.addViewController("/sse").setViewName("/sse");
        registry.addViewController("/async").setViewName("/async");
    }

    /*
        文件上传
     */
    @Bean
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1000000l);
        return multipartResolver;
    }

    /*
        Converter
        配置自定义的HttpMessageConverter的Bean,在Spring MVC里注册HttpMessageConverter有两个方法:
        1. configureMessageConverters:  重载会覆盖掉Spring MVC默认的注册的多个HttpMessageConverter.
        2. extendMessageConverters:     仅添加一个自定义的HttpMessageConverter,不覆盖默认注册的HttpMessageConverter
     */

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(converter());
    }
    @Bean
    public MyMessageConverter converter(){
        return new MyMessageConverter();
    }
}
