package depth.servlet.server;

import depth.servlet.example.ExampleServlet;
import depth.servlet.filter.LoggingFilter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServletApplication {

	public static void main(String[] args) throws Exception {
		ServletServer server = new ServletServer();
		server.registerServlet("/hello", new ExampleServlet());
		server.addFilter(new LoggingFilter()); // 필터 등록
		server.start(8080);
	}

}
