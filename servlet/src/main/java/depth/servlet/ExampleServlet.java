package depth.servlet;

import java.io.IOException;

public class ExampleServlet implements MyServlet {
    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        response.write("Hello from MyServlet!");
    }
}
