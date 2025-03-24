package unidades.unidad5.ejemplos.crearurls;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLStreamHandler;

public class CrearURLs {
    public static void main(String[] args) {
        try {
            // 1. Crear una URL a partir de sus componentes: protocolo, host, puerto, fichero
            URL url1 = new URL("http", "www.iesaugustobriga.org", 80, "index.htm");
            System.out.println("URL 1: " + url1);

            // 2. Crear una URL directamente desde una cadena completa
            URL url2 = new URL("http://www.iesaugustobriga.org");
            System.out.println("URL 2: " + url2);

            // 3. Crear una URL relativa a otra URL base
            URL base = new URL("http://www.iesaugustobriga.org/carpeta/");
            URL url3 = new URL(base, "pagina.html"); // resultado: http://www.iesaugustobriga.org/carpeta/pagina.html
            System.out.println("URL 3 (relativa): " + url3);

            // 4. Crear una URL con un manejador personalizado del protocolo (poco común y avanzado)
            // Este es solo un ejemplo demostrativo sin conexión real:
            //URLStreamHandler handler = new sun.net.www.protocol.http.Handler();
            //URL url4 = new URL("http", "www.iesaugustobriga.org", 80, "/index.htm", handler);
            //System.out.println("URL 4 (con manejador): " + url4);

        } catch (MalformedURLException e) {
            System.out.println("Se ha producido un error al crear la URL:");
            e.printStackTrace();
        }
    }
}
