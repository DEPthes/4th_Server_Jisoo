package depth.servlet.filter;

import depth.servlet.core.HttpRequest;
import depth.servlet.core.HttpResponse;
import depth.servlet.filter.FilterChain;
import depth.servlet.filter.MyFilter;

public class LoggingFilter implements MyFilter {
    @Override
    public void doFilter(HttpRequest request, HttpResponse response, FilterChain chain) throws Exception {
        System.out.println("[Filter] 요청 전: " + request.getPath());
        System.out.println("Processing by: " + Thread.currentThread().getName());
        chain.doFilter(request, response); // 다음 필터 or 서블릿
        System.out.println("[Filter] 응답 후: " + request.getPath());
    }
}