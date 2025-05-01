package depth.servlet;

import java.io.IOException;

public interface MyServlet {
    void service(HttpRequest request, HttpResponse response) throws IOException;
}