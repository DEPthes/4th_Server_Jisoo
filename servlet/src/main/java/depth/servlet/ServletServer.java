package depth.servlet;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServletServer {
    private final Map<String, MyServlet> servletMap = new HashMap<>();

    //스레드풀 필드 추가
    private final ExecutorService threadPool = Executors.newFixedThreadPool(10);
    public void registerServlet(String path, MyServlet servlet) {
        servletMap.put(path, servlet); // 경로 등록
    }

    public void start(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server started at port " + port);
        while (true) {
            Socket socket = serverSocket.accept();
            //new Thread(() -> handle(socket)).start(); // 요청마다 새 스레드
            threadPool.execute(() -> handle(socket)); // 새 스레드 대신 threadPool 사용
        }
    }

    private void handle(Socket socket) {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
        ) {
            HttpRequest request = new HttpRequest(in);     // 요청 파싱
            HttpResponse response = new HttpResponse(out); // 응답 생성

            System.out.println("Processing by: " + Thread.currentThread().getName());

            MyServlet servlet = servletMap.getOrDefault(request.getPath(), new NotFoundServlet());
            servlet.service(request, response); // 서블릿 위임

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
