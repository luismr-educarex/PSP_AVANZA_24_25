package unidades.unidad3.ejemplos.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerManager {
    private final int port;
    private ServerSocket serverSocket;
    private final ClientRegistry clientRegistry;
    private final ExecutorService threadPool;

    public ServerManager(int port) {
        this.port = port;
        this.clientRegistry = new ClientRegistry();
        // Usar un thread pool mejora la eficiencia
        this.threadPool = Executors.newCachedThreadPool();
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Servidor de chat iniciado en el puerto " + port);

            while (!serverSocket.isClosed()) {
                // Acepta conexiones de clientes
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nuevo cliente conectado: " + clientSocket);

                // Crear un nuevo manejador de cliente
                ClientHandler clientHandler = new ClientHandler(clientSocket, clientRegistry);
                
                // Ejecutar el manejador en un hilo del pool
                threadPool.execute(clientHandler);
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        } finally {
            shutdown();
        }
    }

    public void shutdown() {
        try {
            if (serverSocket != null) serverSocket.close();
            threadPool.shutdown();
        } catch (IOException e) {
            System.err.println("Error cerrando el servidor: " + e.getMessage());
        }
    }
}