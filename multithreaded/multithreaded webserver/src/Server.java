import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class Server {

    private static final Logger log = Logger.getLogger(Server.class.getName());
    private final ExecutorService threadPool;

    public Server(int poolSize) {
        threadPool = Executors.newFixedThreadPool(poolSize);
    }

    public void handle(Socket socket) {
        try (
                PrintWriter toClient = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            toClient.println("HTTP/1.1 200 OK");
            toClient.println("Content-Type: text/plain");
            toClient.println();
            toClient.println("HELLO FROM SERVER");
            toClient.flush();

            String line = reader.readLine();
            System.out.println("MESSAGE FROM THE CLIENT : " + line);

            reader.close();
            toClient.close();
            socket.close();

        } catch (IOException e) {
            log.warning(e.getMessage());
        }
    }

    public static void main(String[] args) {
        int port = 8081;
        int pool = 50;
        Server server = new Server(pool);

        try{
            ServerSocket socket = new ServerSocket(port);
            socket.setSoTimeout(100000);

            while (true) {
                System.out.println("SERVER IS LISTENING");
                Socket accept = socket.accept();
                System.out.println("CONNECTION ACCEPTED FROM CLIENT : " + accept.getRemoteSocketAddress());
                server.threadPool.execute(() -> server.handle(accept));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            server.threadPool.shutdown();
        }
    }
}
