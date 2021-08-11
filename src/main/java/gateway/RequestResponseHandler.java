package gateway;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class RequestResponseHandler {
    public void handle(ServerHttpResponse response) {
    }

    public void handle(ServerHttpRequest httpServletRequest) {
    }
}
