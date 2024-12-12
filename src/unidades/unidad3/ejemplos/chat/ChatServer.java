package unidades.unidad3.ejemplos.chat;
// Clase principal que inicia el servidor
public class ChatServer {
    private static final int PORT = 5000;

    public static void main(String[] args) {
        ServerManager serverManager = new ServerManager(PORT);
        serverManager.start();
    }
}