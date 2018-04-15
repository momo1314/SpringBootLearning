package org.test.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.test.demo.data.domain.User;

import javax.annotation.Resource;

@Configuration
public class Config {
    @Bean
    public User getUser(){
        return  new User();
    }

}
