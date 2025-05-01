package depth.servlet.filter;

import depth.servlet.core.HttpRequest;
import depth.servlet.core.HttpResponse;

public interface MyFilter {
    void doFilter(HttpRequest request, HttpResponse response, FilterChain chain) throws Exception;
}