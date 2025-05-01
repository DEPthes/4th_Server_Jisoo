package depth.servlet.listener;

import depth.servlet.core.HttpRequest;

public class RequestLoggerListener implements MyRequestListener{
    @Override
    public void onRequestInitialized(HttpRequest request) {
        System.out.println("[Listener] 요청 시작: " + request.getPath());
    }

    @Override
    public void onRequestDestroyed(HttpRequest request) {
        System.out.println("[Listener] 요청 종료: " + request.getPath());
    }
}
