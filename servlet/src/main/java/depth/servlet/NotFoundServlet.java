package depth.servlet;

import java.io.IOException;

public class NotFoundServlet implements MyServlet {
    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        response.write("404 Not Found");
    }
}
