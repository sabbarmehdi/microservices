package me.sabbar.apigateway.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Mono;

@Log4j2
@Configuration
public class GlobalFiltersConfiguration {

    @Order(1)
    @Bean
    public GlobalFilter secondPreFilter(){
        return ((exchange, chain) -> {
           log.info("Second pre-filter is executed ");
           return chain.filter(exchange).then(Mono.fromRunnable(()->{
               log.info("2ed Post filter is executed... ");
           }));
        });
    }

    @Order(2)
    @Bean
    public GlobalFilter thirdPreFilter(){
        return ((exchange, chain) -> {
            log.info("Third pre-filter is executed ");
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                log.info("3ed Post filter is executed... ");
            }));
        });
    }

    @Order(3)
    @Bean
    public GlobalFilter fourdPreFilter(){
        return ((exchange, chain) -> {
            log.info("fourd pre-filter is executed ");
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                log.info("4ed Post filter is executed... ");
            }));
        });
    }
}
