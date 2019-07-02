package com.jpgough.hackday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import pro.chenggang.plugin.springcloud.gateway.annotation.EnableGatewayPlugin;

@SpringBootApplication
@EnableGatewayPlugin
public class HackdayApplication {

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("path_route", r -> r.path("/get")
						.uri("http://httpbin.org"))

				.route("birthday", r -> r.path("/birthday/**")
				.filters(f -> f.rewritePath("/birthday/(?<segment>.*)", "/${segment}"))
				.uri("http://localhost:8081/"))

				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(HackdayApplication.class, args);
	}

}
