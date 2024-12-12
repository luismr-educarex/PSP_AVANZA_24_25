package unidades.unidad3.ejemplos.chat;
// Clase para manejar el registro de clientes
import java.util.*;
import java.util.concurrent.*;

public class ClientRegistry {
    // CopyOnWriteArrayList es thread-safe para múltiples lecturas y escrituras
    private final List<ClientHandler> clients = new CopyOnWriteArrayList<>();

    public void addClient(ClientHandler client) {
        clients.add(client);
    }

    public void removeClient(ClientHandler client) {
        clients.remove(client);
    }

    public void broadcastMessage(String message, ClientHandler sender) {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }

    public int getClientCount() {
        return clients.size();
    }
}
