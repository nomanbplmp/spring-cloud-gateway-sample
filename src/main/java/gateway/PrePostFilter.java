package gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class PrePostFilter
        implements GatewayFilter, Ordered {

    final Logger logger =
            LoggerFactory.getLogger(PrePostFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange,
                             GatewayFilterChain chain) {
        logger.info("First Pre Global Filter");
        Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        Route newRoute = Route.async()
                .id(route.getId())
                .filters(route.getFilters())
                .uri("forward:/api/hello1")
                .order(route.getOrder())
                .asyncPredicate(route.getPredicate())
                .build();

        exchange.getAttributes().put(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR,newRoute);
        return chain.filter(exchange)
                .then(Mono.fromRunnable(() -> {
                    logger.info("Last Post Global Filter " + exchange.getResponse());
                }));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
