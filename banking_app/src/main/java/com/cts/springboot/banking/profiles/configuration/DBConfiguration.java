package com.cts.springboot.banking.profiles.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Getter
@Setter
@Configuration
@ConfigurationProperties("spring.datasource")
public class DBConfiguration {

    private String driverClassName;
    private String url;
    private String username;
    private String password;

    @Profile("dev")
    @Bean
    public String devDatabaseConnection() {
        System.out.println("DB connection for DEV - H2");
        System.out.println(driverClassName);
        System.out.println(url);
        return "DB connection for DEV - H2";
    }

    @Profile("test")
    @Bean
    public String testDatabaseConnection() {
        System.out.println("DB Connection to TEST -H2");
        System.out.println(driverClassName);
        System.out.println(url);
        return "DB Connection to TEST -H2";
    }
}
