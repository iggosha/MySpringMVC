package com.myspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import java.util.Collections;

@SpringBootApplication
public class MySpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySpringApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean hiddenHttpMethodFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new HiddenHttpMethodFilter());
        filterRegistrationBean.setUrlPatterns(Collections.singletonList("/*"));
        return filterRegistrationBean;
    }
}