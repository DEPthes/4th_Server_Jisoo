package week01.simplehttp.v2;

import java.net.Socket;
// 클라이언트의 요청을 처리하는 스레드 클래스
// RequestParser로 요청을 파싱하고
// ResponseWriter로 응답을 보냄
public class RequestHandler extends Thread {
    private final Socket socket;

    public RequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        // 요청 파싱
        RequestParser parser = new RequestParser(socket);
        Request request = parser.parse();

        // 응답 작성 및 전송
        ResponseWriter writer = new ResponseWriter(socket, request);
        writer.send();
    }
}
