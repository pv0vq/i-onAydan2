package com.example.smple1231.filter;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCorsFilter implements Filter { //CORS 필터
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin")); //도메인 간 요청을 할 수 있는 권한이 부여된 도메인을 지정한다.
        response.setHeader("Access-Control-Allow-Credentials", "true"); //도메인 간 요청에 credential 권한이 있는지 없는지 지정한다.
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, PATCH"); //어떤 헤더 필드 네임이 실제 요청에서 사용할 수 있는지 가리킨다.
        response.setHeader("Access-Control-Max-Age", "3600"); //pre-flighted 요청이 얼마만큼의 시간동안 캐시되는지
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me"); //노출하기에 안전한 헤더를 나타낸다
        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}
