package depth.servlet.core;

import depth.servlet.core.HttpRequest;
import depth.servlet.core.HttpResponse;
import depth.servlet.core.MyServlet;

import java.io.IOException;

public class NotFoundServlet implements MyServlet {
    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        response.write("404 Not Found");
    }
}
