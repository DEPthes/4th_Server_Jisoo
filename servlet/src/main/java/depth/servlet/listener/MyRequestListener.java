package depth.servlet.listener;

import depth.servlet.core.HttpRequest;

public interface MyRequestListener {
    void onRequestInitialized(HttpRequest request);
    void onRequestDestroyed(HttpRequest request);
}
