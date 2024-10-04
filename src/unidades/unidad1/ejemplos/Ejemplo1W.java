package unidades.unidad1.ejemplos;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Ejemplo1W {

    public static void main(String[] args) {
        
        ProcessBuilder pb = new ProcessBuilder();
        Process p = null;
        Map<String, String> entorno = pb.environment();
        
        System.out.println("Variables de entorno");
        System.out.println(entorno);
        
        // Para entornos Windows (usamos cmd.exe /c para ejecutar el comando en la consola)
        pb.command("cmd.exe", "/c", "dir /B | findstr documentos");
        // Cambiamos la ruta al directorio para Windows
        pb.directory(new File("C:\\FICHEROS"));
        
        try {
            
            List<String> l = pb.command();    
            Iterator<String> it = l.iterator();
            
            while(it.hasNext()) {
                System.out.println(it.next());
            }

            // Cambiamos la ruta del archivo de salida
            File fout = new File("C:\\RESULTADOS\\salidaProceso.txt");
            pb.redirectOutput(fout);
            
            p = pb.start();
            
            InputStream is = p.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
