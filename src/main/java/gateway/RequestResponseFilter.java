package gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.filter.OrderedFilter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;

@Component
public class RequestResponseFilter implements GatewayFilter, Ordered {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RequestResponseHandler requestResponseHandler;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest httpServletRequest = (ServerHttpRequest) exchange.getRequest();

        requestResponseHandler.handle(httpServletRequest);

        return chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {
                    requestResponseHandler.handle(exchange.getResponse());
                    System.out.println("Inside local reps modifier");
                }));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}

