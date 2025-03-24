package unidades.unidad5.ejemplos.analizarurl;
import java.net.URL;

public class AnalizarURL {
    public static void main(String[] args) {
        try {
            // Ejemplo de URL
            URL url = new URL("https://www.ejemplo.com:8080/ruta/archivo.html#seccion1");

            // Obtener y mostrar los diferentes componentes de la URL
            System.out.println("URL completa: " + url.toString());
            System.out.println("Protocolo: " + url.getProtocol());
            System.out.println("Host: " + url.getHost());
            System.out.println("Puerto: " + url.getPort()); // -1 si no se especifica
            System.out.println("Puerto por defecto: " + url.getDefaultPort());
            System.out.println("Fichero: " + url.getFile());
            System.out.println("Referencia: " + url.getRef()); // null si no hay referencia

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
