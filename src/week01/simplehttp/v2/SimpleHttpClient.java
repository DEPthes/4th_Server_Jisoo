package week01.simplehttp.v2;

import java.io.*;
import java.net.Socket;

public class SimpleHttpClient {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 8081;

        try (Socket socket = new Socket(host, port);
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            // 요청 전송 (GET /today HTTP/1.1)
            out.write("GET /today HTTP/1.1\r\n");
            out.write("Host: localhost\r\n");
            out.write("\r\n");
            out.flush();

            // 응답 헤더 읽기
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                if (line.isEmpty()) break; // 헤더 끝
            }

            // 응답 본문 출력
            String body = in.readLine();
            if (body != null) {
                System.out.println(body);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
