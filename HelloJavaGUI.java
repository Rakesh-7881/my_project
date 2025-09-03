import java.io.*;
import java.net.*;

public class HelloJavaWeb {
    public static void main(String[] args) throws Exception {
        int port = (args.length > 0) ? Integer.parseInt(args[0]) : 9090;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server started at http://localhost:" + port);

        while (true) {
            Socket client = serverSocket.accept();
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);

            // HTTP Response headers
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: text/html");
            out.println();

            // HTML with colorful Hello
            out.println("<!DOCTYPE html>");
            out.println("<html><head><title>Hello Java</title></head><body style='text-align:center; font-family:Arial;'>");
            out.println("<h1>Hello Java</h1>");
            out.println("<button onclick=\"document.getElementById('hello').innerHTML='<span style=\\'color:red;\\'>H</span><span style=\\'color:orange;\\'>e</span><span style=\\'color:green;\\'>l</span><span style=\\'color:blue;\\'>l</span><span style=\\'color:purple;\\'>o</span>'\">Show Colorful Hello</button>");
            out.println("<h2 id='hello'></h2>");
            out.println("</body></html>");

            out.flush();
            client.close();
        }
    }
}
