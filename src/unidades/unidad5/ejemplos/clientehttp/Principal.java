package unidades.unidad5.ejemplos.clientehttp;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Principal {
    public static void main(String[] args) throws Exception {
        // Obtener la dirección IP del servidor
        InetAddress direccion = InetAddress.getByName("www.google.com");

        // Crear un socket para conectarse al servidor en el puerto 80 (HTTP)
        Socket socket = new Socket(direccion, 80);
        
        boolean autoVaciado = true;

        // Flujo de salida para enviar datos al servidor
        PrintWriter salida = new PrintWriter(socket.getOutputStream(), autoVaciado);

        // Flujo de entrada para recibir datos del servidor
        BufferedReader entrada = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

        // Enviar una solicitud HTTP al servidor web
        salida.println("GET / HTTP/1.1");
        salida.println("Host: www.google.com:80");
        salida.println("Connection: Close");
        salida.println(); // Línea en blanco para indicar el final del encabezado

        // Leer la respuesta del servidor
        boolean continuar = true;
        StringBuilder respuesta = new StringBuilder(8096);
        
        while (continuar) {
            if (entrada.ready()) { // Verificar si hay datos disponibles para leer
                int caracter = 0;
                while (caracter != -1) { // Leer hasta el final de la respuesta
                    caracter = entrada.read();
                    respuesta.append((char) caracter);
                }
                continuar = false;
            }
        }

        // Imprimir la respuesta del servidor
        System.out.println(respuesta.toString());

        // Cerrar el socket
        socket.close();
    }
}
