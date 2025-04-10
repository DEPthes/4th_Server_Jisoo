package week01.simplehttp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleHttpServer {
    public static void main(String[] args) {
        int port = 8080;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("서버가 포트 " + port + "에서 대기 중...");

            while (true) {
                Socket clientSocket = serverSocket.accept();  // 클라이언트 접속 대기
                System.out.println("클라이언트 연결됨: " + clientSocket);

                // 요청을 읽기 위한 입력 스트림
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                // 응답을 보내기 위한 출력 스트림
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                // 요청 라인 읽기 (예: GET / HTTP/1.1)
                String requestLine = in.readLine();
                System.out.println("요청 라인: " + requestLine);

                // 간단한 GET 요청일 경우 응답 작성
                if (requestLine != null && requestLine.startsWith("GET")) {
                    String body = "Hello from the server!"; // 응답 본문
                    String response = "HTTP/1.1 200 OK\r\n" + // 상태
                            "Content-Type: text/plain; charset=UTF-8\r\n" + // 헤더
                            "Content-Length: " + body.getBytes().length + "\r\n" +
                            "\r\n" + // 헤더와 본문 구분
                            body; // 본문

                    out.write(response); // 응답 전송
                    out.flush();
                }

                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

