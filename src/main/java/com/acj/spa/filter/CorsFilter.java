package com.acj.spa.filter;

import lombok.RequiredArgsConstructor;
import org.codehaus.plexus.util.StringUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
public class CorsFilter implements Filter {

    private final CorsProperties corsProperties;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        Optional<String> headerReferer = Optional.ofNullable(request.getHeader("Referer"));
        Optional<String> headerOrigin = Optional.ofNullable(request.getHeader("Origin"));

        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN");

        if (headerReferer.isPresent() && headerOrigin.isPresent()) {
            final String origin = headerOrigin.orElseThrow(() -> new ServletException("Origin não informada!"));
            response.setHeader("Access-Control-Allow-Origin", origin);
            final Boolean originAllowed = corsProperties.getAllowedOrigins()
                    .stream()
                    .filter(allowedOrigin -> StringUtils.equals(allowedOrigin, origin))
                    .count() > 0L;
            if (!originAllowed) {
                throw new ServletException("Origin informada, não permitida!");
            }
        }

        if ("OPTIONS".equals(request.getMethod())) {
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT, OPTIONS, HEAD");

            List<String> accessControlAllowHeadersList = new ArrayList<>(Arrays.asList(
                    "Authorization",
                    "Content-Type",
                    "Accept",
                    "X-REQUESTED-WITH",
                    "Origin"
            ));
            Optional<String> accessControlRequestHeaders = Optional.ofNullable(request.getHeader("Access-Control-Request-Headers"));
            accessControlRequestHeaders.ifPresent(
                    header -> Stream.of(header.split(",")).parallel().forEach(accessControlAllowHeadersList::add)
            );
            response.setHeader("Access-Control-Allow-Headers", String.join(",", accessControlAllowHeadersList));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}

