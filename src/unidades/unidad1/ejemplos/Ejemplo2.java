package unidades.unidad1.ejemplos;

import java.io.File;

public class Ejemplo2 {

    public static void main(String[] args) {
        
        Process p = null;
        // Usamos cmd.exe para ejecutar comandos en Windows
        ProcessBuilder pb = new ProcessBuilder("cmd.exe");

        try {
            // Cambiamos las rutas de los archivos a formato de Windows
            File fout = new File("C:\\PSP\\RESULTADOS\\resultado.txt");
            File ferror = new File("C:\\PSP\\RESULTADOS\\errores.txt");
            File fin = new File("C:\\PSP\\PROGRAMAS\\miPrograma.bat");
            
            // Redirigimos salida, error e input
            pb.redirectOutput(fout);
            pb.redirectError(ferror);
            pb.redirectInput(fin);
            
            // Iniciamos el proceso
            p = pb.start();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
