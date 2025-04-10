package week01.simplehttp.v1;

import java.io.*;
import java.net.Socket;

public class SimpleHttpClient {
    public static void main(String[] args) {
        String host = "127.0.0.1"; // 접속할 서버의 주소
        int port = 8080; // 포트

        try (Socket socket = new Socket(host, port);
             // 서버로 요청을 보내기 위한 출력 스트림
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             //서버로부터 응답을 받기 위한 입력 스트림
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            // 간단한 HTTP GET 요청 전송
            out.write("GET / HTTP/1.1\r\n"); // HTTP 요청 라인
            out.write("Host: localhost\r\n"); // Host 헤더
            out.write("\r\n"); //헤더와 본문 구분하는 줄
            out.flush(); //버퍼 비우기

            // 응답 수신 및 출력
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line); //응답 헤더 출력
                if (line.isEmpty()) break; // 헤더 끝
            }

            // 본문 출력 (한 줄 더 받을 경우)
            String bodyLine = in.readLine();
            if (bodyLine != null) {
                System.out.println(bodyLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
