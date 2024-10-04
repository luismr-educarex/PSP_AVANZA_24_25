package unidades.unidad1.ejemplos;
import java.util.concurrent.TimeUnit;

public class Ejemplo3 {
    
    public static void main(String[] args) {
    
        Runtime r = Runtime.getRuntime();
        // Cambia el comando para ejecutar Firefox en Windows.
        String comando = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
        
        Process p;
        
        try {
            // Ejecutamos el comando (Firefox)
            p = r.exec(comando);
            
            // Esperamos hasta 10 segundos para ver si el proceso se cierra solo
            if (p.waitFor(10, TimeUnit.SECONDS)) {
                System.out.println("Firefox se ha cerrado previamente");
            } else {
                // Si no se cierra, destruimos el proceso
                p.destroy();
                System.out.println("Firefox fue destruido tras 10 segundos");
            }
            
        } catch (Exception e) {
            System.out.println("Error en: " + comando);
            e.printStackTrace();
        }
    
    }
}
