package gateway;

import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.factory.rewrite.ModifyResponseBodyGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.rewrite.RewriteFunction;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

@Component
public class ResponseModifierFunction implements RewriteFunction<String, String>, Consumer<ModifyResponseBodyGatewayFilterFactory.Config> {
    Logger LOGGER = LoggerFactory.getLogger(RequestModifierFunction.class);
    @Override
    public Publisher<String> apply(ServerWebExchange serverWebExchange, String s) {
        LOGGER.info("Inside resonse modifier");

        return Mono.just(s);
    }

    @Override
    public void accept(ModifyResponseBodyGatewayFilterFactory.Config config) {

        config.setRewriteFunction(String.class,String.class,this);
    }
}
