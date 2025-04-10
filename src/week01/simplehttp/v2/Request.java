package week01.simplehttp.v2;

public class Request {
    private final String method;
    private final String uri;
    private final String httpVersion;

    public Request(String method, String uri, String httpVersion) {
        this.method = method;
        this.uri = uri;
        this.httpVersion = httpVersion;
    }

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public String getHttpVersion() {
        return httpVersion;
    }
}
