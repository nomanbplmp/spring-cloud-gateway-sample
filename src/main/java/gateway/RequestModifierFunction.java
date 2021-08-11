package gateway;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.factory.rewrite.ModifyRequestBodyGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.rewrite.RewriteFunction;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

@Component
public class RequestModifierFunction implements RewriteFunction<String, String>,
        Consumer<ModifyRequestBodyGatewayFilterFactory.Config> {
    Logger LOGGER = LoggerFactory.getLogger(RequestModifierFunction.class);
    @Override
    public Publisher<String> apply(ServerWebExchange serverWebExchange, String s) {
        LOGGER.info("Inside modifier");
        return Mono.just("test");
    }

    @Override
    public void accept(ModifyRequestBodyGatewayFilterFactory.Config config) {
        config.setRewriteFunction(String.class,String.class,this);
    }
}
