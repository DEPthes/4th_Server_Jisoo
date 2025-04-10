package week01.simplehttp.v2;
//요청 정보를 저장하는 DTO 클래스
// HTTP Method (GET, POST 등)
// URI (요청 경로)
// HTTP 버전
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
