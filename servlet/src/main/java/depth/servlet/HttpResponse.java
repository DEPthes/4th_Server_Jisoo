package depth.servlet;

import java.io.BufferedWriter;
import java.io.IOException;

public class HttpResponse {
    private final BufferedWriter writer;

    public HttpResponse(BufferedWriter writer) {
        this.writer = writer;
    }

    public void write(String body) throws IOException {
        writer.write("HTTP/1.1 200 OK\r\n");
        writer.write("Content-Type: text/plain\r\n");
        writer.write("Content-Length: " + body.length() + "\r\n");
        writer.write("\r\n");
        writer.write(body);
        writer.flush();
    }
}
