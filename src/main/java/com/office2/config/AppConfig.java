// src/main/java/com/office2/config/AppConfig.java
package com.office2.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.office2.service",
        "com.office2.dao"
})
public class AppConfig {
    // дополнительные бины, если будут нужны
}
