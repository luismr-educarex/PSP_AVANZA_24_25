package unidades.unidad2.ejemplos.colecciones;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;

public class Alfabetos {
    public static void main(String[] args) {
        // Declaración de una BlockingQueue de naturaleza 'acotada' usando ArrayBlockingQueue
        BlockingQueue<String> colaAlfabetos = new ArrayBlockingQueue<>(7);

        try {
            // Insertar elementos en la cola bloqueante
            colaAlfabetos.put("A");
            colaAlfabetos.put("B");
            colaAlfabetos.put("C");
            colaAlfabetos.put("D");
            colaAlfabetos.put("E");
            colaAlfabetos.put("F");
            colaAlfabetos.put("G");

            System.out.println("Contenido de la BlockingQueue: " + colaAlfabetos);

            // Eliminar un elemento de la cola
            String temp = colaAlfabetos.take();
            System.out.println("El elemento eliminado es: " + temp);

            // Contenido de la BlockingQueue después de eliminar un elemento
            System.out.println("Contenido de la BlockingQueue después de eliminar un elemento: " + colaAlfabetos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
