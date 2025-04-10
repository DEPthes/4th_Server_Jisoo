package week01.simplehttp.v2;

import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
    public static void main(String[] args) {
        int port = 8081;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("서버가 포트 " + port + "에서 대기 중...");

            while (true) {
                // 클라이언트 요청 수락
                Socket socket = serverSocket.accept();
                // 새로운 스레드에서 요청 처리
                new RequestHandler(socket).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
