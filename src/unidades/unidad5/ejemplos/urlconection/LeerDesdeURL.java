package unidades.unidad5.ejemplos.urlconection;
import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LeerDesdeURL {
    public static void main(String[] args) {
        try {
            // Crear objeto URL
            URL url = new URL("https://iesaugustobriga.educarex.es/");

            // Abrir conexión
            URLConnection conexion = url.openConnection();

            // Leer el contenido
            BufferedReader lector = new BufferedReader(
                new InputStreamReader(conexion.getInputStream()));

            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }

            lector.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
