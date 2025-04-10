package week01.simplehttp.v2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class RequestParser {
    private final Socket socket;

    public RequestParser(Socket socket) {
        this.socket = socket;
    }

    public Request parse() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = reader.readLine(); // 예: GET /today HTTP/1.1

            if (line == null || line.isEmpty()) return null;

            String[] parts = line.split(" ");
            if (parts.length == 3) {
                return new Request(parts[0], parts[1], parts[2]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
