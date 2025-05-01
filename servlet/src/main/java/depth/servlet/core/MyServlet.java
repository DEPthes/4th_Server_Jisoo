package depth.servlet.core;

import depth.servlet.core.HttpRequest;
import depth.servlet.core.HttpResponse;

import java.io.IOException;

public interface MyServlet {
    void service(HttpRequest request, HttpResponse response) throws IOException;
}