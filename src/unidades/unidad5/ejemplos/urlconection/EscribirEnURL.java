package unidades.unidad5.ejemplos.urlconection;
import java.net.URL;
import java.net.URLConnection;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class EscribirEnURL {
    public static void main(String[] args) {
        try {
            // URL que acepte POST (puedes usar una API de pruebas como https://httpbin.org/post)
            URL url = new URL("https://httpbin.org/post");

            // Abrir conexión
            URLConnection conexion = url.openConnection();
            conexion.setDoOutput(true); // Habilitar escritura (POST)

            // Enviar datos
            OutputStreamWriter escritor = new OutputStreamWriter(conexion.getOutputStream());
            escritor.write("nombre=Luis&mensaje=HolaDesdeJava");
            escritor.flush();

            // Leer respuesta
            BufferedReader lector = new BufferedReader(
                new InputStreamReader(conexion.getInputStream()));
            
            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }

            escritor.close();
            lector.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
