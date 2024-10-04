package unidades.unidad1.ejemplos.lanzarprocesos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Aplicacion {

	public static void main(String[] args) {
		
		try {
			
		ProcessBuilder pb = new ProcessBuilder();
		pb.command("C:\\PSP\\PROGRAMAS\\proceso_python.py");
        Process proceso = null;
        proceso = pb.start();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
		
		proceso.waitFor();
		int exitStatus = proceso.exitValue();
		System.out.println("Retorno:"+br.readLine());
		System.out.println("Valor de la salida:"+exitStatus);
		}catch(IOException | InterruptedException e) {
			e.printStackTrace();
		
		}

	}

}
