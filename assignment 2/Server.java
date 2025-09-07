import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        System.out.println("Server started. Waiting for clients...");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

              
                ClientHandler handler = new ClientHandler(clientSocket);
                handler.start(); // last commit done by sabeer at 11:46 PM

                // No .join() — allows multiple clients simultaneously
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// last commit done by sabeer at 11:46 PM
