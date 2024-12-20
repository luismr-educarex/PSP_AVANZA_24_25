package unidades.unidad3.ejemplos.upd;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class EmisorUDP {
    public static void main(String[] args) {
        final String IP_RECEPTOR = "127.0.0.1"; // Dirección del receptor (localhost)
        final int PUERTO_RECEPTOR = 1500;       // Puerto donde escucha el receptor

        try (DatagramSocket socket = new DatagramSocket();
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("(EmisorUDP) Listo para enviar mensajes. Escribe 'salir' para terminar.");

            while (true) {
                // Leer mensaje desde la entrada estándar
                System.out.print("(EmisorUDP) Escribe un mensaje: ");
                String mensaje = scanner.nextLine();

                // Terminar si el usuario escribe "salir"
                if (mensaje.equalsIgnoreCase("salir")) {
                    System.out.println("(EmisorUDP) Finalizando.");
                    break;
                }

                // Convertir el mensaje a bytes
                byte[] buffer = mensaje.getBytes();

                // Crear un paquete con el mensaje, dirección y puerto del receptor
                InetAddress direccion = InetAddress.getByName(IP_RECEPTOR);
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, direccion, PUERTO_RECEPTOR);

                // Enviar el paquete
                socket.send(paquete);
                System.out.println("(EmisorUDP) Mensaje enviado a " + IP_RECEPTOR + ":" + PUERTO_RECEPTOR);
            }
        } catch (Exception e) {
            System.err.println("(EmisorUDP) Error: " + e.getMessage());
        }
    }
}
