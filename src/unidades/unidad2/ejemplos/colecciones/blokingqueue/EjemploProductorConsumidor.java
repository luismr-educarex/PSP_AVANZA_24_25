package unidades.unidad2.ejemplos.colecciones.blokingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class EjemploProductorConsumidor {
    static class DatoProducido {
        // Clase de datos vacía
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<DatoProducido> cola = new ArrayBlockingQueue<>(1000);
        
        // Crear hilos de productor y consumidor
        Thread productor = new Thread(new Productor(cola));
        Thread consumidor = new Thread(new Consumidor(cola));

        // Iniciar los hilos
        productor.start();
        consumidor.start();

        // Ejecutar por un tiempo y luego interrumpir
        Thread.sleep(1000);
        productor.interrupt();

        // Dar un poco de tiempo para que el consumidor termine de procesar los elementos restantes
        consumidor.interrupt();
        consumidor.join();  // Esperar a que el consumidor termine
    }
}
