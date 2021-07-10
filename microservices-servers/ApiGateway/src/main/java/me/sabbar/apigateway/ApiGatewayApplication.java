package me.sabbar.apigateway;

import me.sabbar.apigateway.security.AuthorizationHeaderFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder rlb, AuthorizationHeaderFilter
            authorizationHeaderFilter) {

        return rlb
                .routes()
                .route(p -> p
                        .path("/user/check")
                        .filters(f -> f.removeRequestHeader("Cookie")
                                //.rewritePath("/users-service/(?<segment>.*)", "/$\\{segment}")
                                .filter(authorizationHeaderFilter.apply(new
                                        AuthorizationHeaderFilter.Config())))
                        .uri("lb://users-service")
                )
                .build();
    }
}
