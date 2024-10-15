package unidades.unidad1.tareas.tarea1;
import java.util.Random;

public class GenerarPalabras {
    public static void main(String[] args) {
        Random random = new Random();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int wordCount = 30;
        
        for (int i = 0; i < wordCount; i++) {
            int wordLength = random.nextInt(6) + 3;  // Generar palabras de 3 a 8 caracteres
            StringBuilder word = new StringBuilder(wordLength);
            for (int j = 0; j < wordLength; j++) {
                word.append(alphabet.charAt(random.nextInt(alphabet.length())));
            }
            System.out.println(word.toString());
        }
    }
}
