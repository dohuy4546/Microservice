package com.husony.API_Gateway.filter;

import com.husony.API_Gateway.component.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtService jwtService;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = null;
            System.out.println("outside");
            if (validator.isSecured.test(exchange.getRequest())) {
                System.out.println("okkkkkk");
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                System.out.println(authHeader);
                try {
                    jwtService.validateTokenLogin(authHeader);
                    request = exchange.getRequest()
                            .mutate()
                            .header("username", jwtService.getUsernameFromToken(authHeader))
                            .header("user_role", jwtService.getUserRoleFromToken(authHeader))
                            .build();
                    System.out.println(jwtService.getUserRoleFromToken(authHeader));
                    log.info("Info username: {}", jwtService.getUsernameFromToken(authHeader));
                    log.info("Info role: {}", jwtService.getUserRoleFromToken(authHeader));
                    System.out.println(jwtService.getUsernameFromToken(authHeader));
                    System.out.println(jwtService.getUserRoleFromToken(authHeader));
                } catch (Exception e) {
                    System.out.println("invalid access...!");
                    throw new RuntimeException("un authorized access to application");
                }
            }
            return chain.filter(exchange.mutate().request(request).build());
        });
    }

    public static class Config{

    }
}
