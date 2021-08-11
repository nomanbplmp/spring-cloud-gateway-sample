package gateway;

import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

// tag::code[]
@SpringBootApplication
public class Application {

	@Autowired
	private RequestResponseFilter requestHandler;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private RequestModifierFunction requestModifierFunction;

	@Autowired
	private ResponseModifierFunction  responseModifierFunction;


	@Autowired
	private PrePostFilter  prePostFilter;

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {

		return builder.routes()

			.route(p -> p.path("/*")
				.filters(f->f
						.filter(prePostFilter)
						.modifyRequestBody(requestModifierFunction)
						.modifyResponseBody(responseModifierFunction))
				.uri("no://op"))
				.build();
	}

	private String getEncode()  {

			return "http://localhost:9999/api/test";

	}


	// tag::fallback[]
	@RequestMapping("/fallback")
	public Mono<String> fallback() {
		return Mono.just("fallback");
	}
	// end::fallback[]
}



// end::uri-configuration[]
// end::code[]