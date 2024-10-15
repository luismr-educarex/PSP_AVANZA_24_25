package unidades.unidad1.tareas.tarea1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class OrdenarPalabras {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> palabras = new ArrayList<>();

        while (scanner.hasNextLine()) {
            palabras.add(scanner.nextLine());
        }

        Collections.sort(palabras);

        for (String palabra : palabras) {
            System.out.println(palabra);
        }
    }
}
