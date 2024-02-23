import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void request() throws IOException {
        int port = 8080;
        InetAddress inetAddress = InetAddress.getByName("localhost");

        Socket socket = new Socket(inetAddress,port);

        PrintWriter toSocket = new PrintWriter(socket.getOutputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));


        toSocket.println("HELLO FROM THE CLIENT");
        toSocket.flush();


        String line = reader.readLine();
        System.out.println("RESPONSE FROM THE SOCKET IS : "+line);

        toSocket.close();
        reader.close();
        socket.close();
    }


    public Runnable getRunnable(){

        return new Runnable() {
            @Override
            public void run() {
                try {
                    request();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    public static void main(String[] args) {

        Client client = new Client();
        try{

            for(int i = 0 ; i< 1000 ; i++){
                try {
                    Thread thread = new Thread(client.getRunnable());
                    thread.start();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
