package com.example.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("auth-service", r -> r.path("/auth/**")
                .uri("http://localhost:8081"))
            .route("facility-service", r -> r.path("/facilities/**")
                .uri("http://localhost:8082"))
            .route("booking-service", r -> r.path("/bookings/**")
                .uri("http://localhost:8083"))
            .route("user-service", r -> r.path("/user/**")
                .uri("http://localhost:8084"))
            .build();
    }
}
