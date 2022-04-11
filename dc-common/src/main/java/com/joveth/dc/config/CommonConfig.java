package com.joveth.dc.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

/**
 * @author Joveth
 * @date 2022-03-18 17:16
 */
@EnableTransactionManagement
@Configuration
public class CommonConfig {
    @Bean(name = "restTemplate")
    @ConditionalOnMissingBean(name = "restTemplate")
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(Duration.ofMillis(5000))
                .setReadTimeout(Duration.ofMillis(5000))
                .build();
    }
}
