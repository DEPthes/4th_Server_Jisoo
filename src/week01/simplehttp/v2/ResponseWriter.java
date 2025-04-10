package week01.simplehttp.v2;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Date;

public class ResponseWriter {
    private final Socket socket;
    private final Request request;

    public ResponseWriter(Socket socket, Request request) {
        this.socket = socket;
        this.request = request;
    }

    public void send() {
        try {
            PrintStream out = new PrintStream(socket.getOutputStream());

            String body;
            if (request == null) {
                body = "잘못된 요청입니다.";
            } else {
                switch (request.getUri()) {
                    case "/":
                        body = "정상적으로 응답되었습니다.";
                        break;
                    case "/today":
                        body = "오늘은 " + new Date();
                        break;
                    default:
                        body = "유효하지 않은 경로입니다: " + request.getUri();
                }
            }

            // HTTP 응답 헤더 전송
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: text/plain; charset=UTF-8");
            out.println("Content-Length: " + body.getBytes().length);
            out.println(); // 빈 줄 -> 헤더와 바디 구분
            out.print(body); // 본문 전송

            out.flush();
            out.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
