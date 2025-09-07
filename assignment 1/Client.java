import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        ) {
            String serverMessage;
            while ((serverMessage = input.readLine()) != null) {
                System.out.println("Server: " + serverMessage);
                if (serverMessage.contains("Goodbye")) break;

                System.out.print("You: ");
                String userInput = console.readLine();
                output.println(userInput);
                if ("exit".equalsIgnoreCase(userInput.trim())) break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// last commit done by sabeer at 11:46 PM
