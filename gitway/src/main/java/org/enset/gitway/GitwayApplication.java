package org.enset.gitway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GitwayApplication {

    public static void main(String[] args) {

        SpringApplication.run(GitwayApplication.class, args);
    }

    // configuration des routes to autre MS Statique
/*    @Bean
    RouteLocator routeLocator(RouteLocatorBuilder builder){
        return  builder.routes()
                .route((r)-> r.path("/customers/**").uri("http://localhost:8081/"))
                .route((r)-> r.path("/products/**").uri("http://localhost:8082/"))
                .build();
    }*/
    // configuration des routes dynamique avec load balancer
    // configuration des routes to autre MS Dynamique
 /*   @Bean
    RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route((r) -> r.path("/customers/**").uri("lp://customer-service"))
                .route((r) -> r.path("/products/**").uri("lp://inventory-service"))
                .build();
    }*/
    // Configuration dynamique sans connait les MS et leurs nom
    /*
    * ACCES TO CUSTOMERS
    * http://localhost:8888/INVENTORY-SERVICE/products
    * you should to write the name of MS Uppercase 
    * */
    @Bean
    DiscoveryClientRouteDefinitionLocator dynamicRoutes(ReactiveDiscoveryClient rdc,
                                                        DiscoveryLocatorProperties dlp){
        return new DiscoveryClientRouteDefinitionLocator(rdc,dlp);
    }

}
