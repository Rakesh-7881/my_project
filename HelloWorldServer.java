import java.io.*;
import java.net.*;

public class HelloWorldServer {
    public static void main(String[] args) throws Exception {
        int port = (args.length > 0) ? Integer.parseInt(args[0]) : 9090;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server started at http://localhost:" + port);

        while (true) {
            Socket client = serverSocket.accept();
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: text/html");
            out.println();
            out.println("<h1>Rakesh Kadam heare!</h1>");
            out.flush();
            client.close();
        }
    }
}
