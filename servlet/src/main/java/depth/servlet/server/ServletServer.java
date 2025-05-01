package depth.servlet.server;

import depth.servlet.filter.FilterChain;
import depth.servlet.filter.MyFilter;
import depth.servlet.core.HttpRequest;
import depth.servlet.core.HttpResponse;
import depth.servlet.core.MyServlet;
import depth.servlet.core.NotFoundServlet;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServletServer {
    private final Map<String, MyServlet> servletMap = new HashMap<>();
    private final List<MyFilter> filters = new ArrayList<>(); // 필터 목록 추가
    private final ExecutorService threadPool = Executors.newFixedThreadPool(10); //스레드풀 필드 추가
    public void registerServlet(String path, MyServlet servlet) {
        servletMap.put(path, servlet); // 경로 등록
    }

    // 필터 등록 메서드
    public void addFilter(MyFilter filter) {
        filters.add(filter);
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

            // 요청에 해당하는 서블릿 찾기
            MyServlet servlet = servletMap.getOrDefault(request.getPath(), new NotFoundServlet());
//            servlet.service(request, response); // 서블릿 위임
            // 필터 체인 생성 후 실행
            FilterChain chain = new FilterChain(filters, servlet);
            chain.doFilter(request, response);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
