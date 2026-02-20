package com.product.security;

public class SecurityConstants {

    public static final String[] PUBLIC_URLS = {
    		"/api/v1/auth/**",

            // Swagger
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/v3/api-docs/**",
            "/api-docs/**"
    };

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
