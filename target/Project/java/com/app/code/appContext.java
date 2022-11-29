package com.app.code;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;


@Configuration
public class appContext {
    @Bean
    public Customer customer(){
        return new Customer();
    }

    @Bean
    public User user(){
        return new User(customer());
    }

    @Scope("prototype")
    @Bean
    public Product product(){
        return new Product();
    }

    @Scope("prototype")
    @Bean
    public Order order(){
        return new Order();
    }
    public appContext() { }

    @Scope("prototype")
    @Bean
    public UserRegistration userRegistration() {
        return new UserRegistration();
    }

    @Scope("prototype")
    @Bean
    public doCommand doANewCommand() { return new doCommand(); }
}
