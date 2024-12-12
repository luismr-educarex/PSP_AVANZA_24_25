package unidades.unidad3.ejemplos.chat;
// Clase para manejar la comunicación individual con cada cliente
import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
    private final Socket socket;
    private final ClientRegistry clientRegistry;
    private BufferedReader input;
    private PrintWriter output;
    private String username;

    public ClientHandler(Socket socket, ClientRegistry clientRegistry) {
        this.socket = socket;
        this.clientRegistry = clientRegistry;
        
        try {
            // Configurar streams de entrada y salida
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            System.err.println("Error configurando comunicación con cliente: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            // Solicitar nombre de usuario
            output.println("Ingrese su nombre de usuario:");
            username = input.readLine();
            
            // Registrar cliente
            clientRegistry.addClient(this);
            
            // Notificar nueva conexión
            String joinMessage = username + " se ha unido al chat";
            clientRegistry.broadcastMessage(joinMessage, this);
            
            // Bucle de recepción de mensajes
            String message;
            while ((message = input.readLine()) != null) {
                clientRegistry.broadcastMessage(username + ": " + message, this);
            }
        } catch (IOException e) {
            System.err.println("Error en comunicación con " + username);
        } finally {
            // Limpiar conexión
            disconnect();
        }
    }

    public void sendMessage(String message) {
        output.println(message);
    }

    private void disconnect() {
        try {
            // Remover cliente del registro
            clientRegistry.removeClient(this);
            
            // Notificar desconexión
            String leaveMessage = username + " ha salido del chat";
            clientRegistry.broadcastMessage(leaveMessage, this);
            
            // Cerrar recursos
            if (socket != null) socket.close();
        } catch (IOException e) {
            System.err.println("Error al desconectar cliente: " + e.getMessage());
        }
    }
}