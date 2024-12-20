package unidades.unidad3.ejemplos.upd;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReceptorUDP {
    public static void main(String[] args) {
        final int PUERTO = 1500; // Puerto donde escucha el receptor
        byte[] buffer = new byte[1024]; // Buffer para recibir mensajes

        try (DatagramSocket socket = new DatagramSocket(PUERTO)) {
            System.out.println("(ReceptorUDP) Esperando mensajes en el puerto " + PUERTO + "...");

            while (true) {
                // Crear un paquete para recibir datos
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);

                // Recibir el mensaje
                socket.receive(paquete);

                // Convertir los datos del paquete a cadena
                String mensaje = new String(paquete.getData(), 0, paquete.getLength());
                System.out.println("(ReceptorUDP) Mensaje recibido desde " + paquete.getAddress() + ": " + mensaje);
            }
        } catch (Exception e) {
            System.err.println("(ReceptorUDP) Error: " + e.getMessage());
        }
    }
}
