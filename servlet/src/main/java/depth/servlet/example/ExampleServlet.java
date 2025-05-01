package depth.servlet.example;

import depth.servlet.core.HttpRequest;
import depth.servlet.core.HttpResponse;
import depth.servlet.core.MyServlet;

import java.io.IOException;

public class ExampleServlet implements MyServlet {
    @Override
    public void service(HttpRequest request, HttpResponse response) throws IOException {
        response.write("Hello from MyServlet!");
    }
}
