package unidades.unidad3.ejemplos.servidor;

import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        final int PUERTO = 49171; // Puerto en el que el servidor escucha conexiones
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("(Servidor) Esperando conexión...");
            
            // Aceptar la conexión del cliente
            try (Socket socket = servidor.accept()) {
                System.out.println("(Servidor) Cliente conectado desde " + socket.getInetAddress());

                // Flujo para recibir datos del cliente
                InputStream entrada = socket.getInputStream();
                OutputStream salida = socket.getOutputStream();

                // Leer el mensaje enviado por el cliente (1 byte)
                int mensajeRecibido = entrada.read();
                System.out.println("(Servidor) Mensaje recibido del cliente: " + mensajeRecibido);

                // Enviar respuesta al cliente
                int mensajeAEnviar = mensajeRecibido + 1; // Respuesta incrementando el valor
                salida.write(mensajeAEnviar);
                System.out.println("(Servidor) Mensaje enviado al cliente: " + mensajeAEnviar);
            }

        } catch (IOException e) {
            System.err.println("(Servidor) Error: " + e.getMessage());
        }
    }
}
