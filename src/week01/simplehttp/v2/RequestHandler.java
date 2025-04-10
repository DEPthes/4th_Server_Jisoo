package week01.simplehttp.v2;

import java.net.Socket;

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
