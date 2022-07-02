package com.example.eventmicroservice;

import com.example.eventmicroservice.config.AxonConfig;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.reactive.function.client.WebClient;


@OpenAPIDefinition
@EnableEurekaClient
@Import({AxonConfig.class})
@SpringBootApplication
public class EventMicroserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EventMicroserviceApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }

}
