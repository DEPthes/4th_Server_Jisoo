package depth.servlet;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpRequest {
    private final String method;
    private final String path;

    public HttpRequest(BufferedReader reader) throws IOException {
        String line = reader.readLine(); // ex) GET /hello HTTP/1.1
        String[] parts = line.split(" ");
        method = parts[0];
        path = parts[1];

        while (!(line = reader.readLine()).isEmpty()) {
            // Header 생략 가능, 필요하면 파싱 추가
        }
    }

    public String getMethod() { return method; }
    public String getPath() { return path; }
}
