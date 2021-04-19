package diasoft.digitalq.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
						.path("/v1/sms-verification")
						.filters(f -> {
							f.addRequestParameter("ProcessGUID", "b713c2ac-0895-4dd5-b631-a21fade444e9");
							f.addRequestParameter("Code", "7951");
							return f;
						})
						.uri("http://localhost:7081"))
				.build();
	}

}
