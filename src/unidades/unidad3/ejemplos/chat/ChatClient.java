package unidades.unidad3.ejemplos.chat;
// Cliente de ejemplo (opcional)
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    private static final String HOST = "localhost";
    private static final int PORT = 5000;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORT);
             BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter serverOutput = new PrintWriter(socket.getOutputStream(), true);
             Scanner consoleInput = new Scanner(System.in)) {

            // Hilo para recibir mensajes del servidor
            new Thread(() -> {
                try {
                    String message;
                    while ((message = serverInput.readLine()) != null) {
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    System.err.println("Error recibiendo mensajes del servidor");
                }
            }).start();

            // Enviar mensajes al servidor
            while (true) {
                String message = consoleInput.nextLine();
                serverOutput.println(message);
            }
        } catch (IOException e) {
            System.err.println("Error conectando al servidor: " + e.getMessage());
        }
    }
}