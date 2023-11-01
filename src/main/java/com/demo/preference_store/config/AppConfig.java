package com.demo.preference_store.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("app")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppConfig {
    DatabaseConfig dbConfig;
}