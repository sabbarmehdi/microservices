package me.sabbar.appdiscovryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class AppDiscovryServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppDiscovryServerApplication.class, args);
    }

}
