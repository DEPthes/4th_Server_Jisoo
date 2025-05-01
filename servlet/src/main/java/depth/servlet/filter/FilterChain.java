package depth.servlet.filter;

import depth.servlet.core.HttpRequest;
import depth.servlet.core.HttpResponse;
import depth.servlet.core.MyServlet;
import depth.servlet.filter.MyFilter;

import java.util.List;

public class FilterChain {
    private final List<MyFilter> filters;
    private final MyServlet servlet;
    private int index = 0;

    public FilterChain(List<MyFilter> filters, MyServlet servlet) {
        this.filters = filters;
        this.servlet = servlet;
    }

    public void doFilter(HttpRequest request, HttpResponse response) throws Exception {
        if (index < filters.size()) {
            MyFilter nextFilter = filters.get(index++);
            nextFilter.doFilter(request, response, this); // 다음 필터 실행
        } else {
            servlet.service(request, response); // 필터 끝 → 실제 서블릿 실행
        }
    }
}
