package unidades.unidad2.ejemplos.colecciones.blokingqueue;
import java.util.concurrent.BlockingQueue;

import unidades.unidad2.ejemplos.colecciones.blokingqueue.EjemploProductorConsumidor.DatoProducido;

// Clase Productor
public class Productor implements Runnable {
    private final BlockingQueue<DatoProducido> cola;

    public Productor(BlockingQueue<DatoProducido> cola) {
        this.cola = cola;
    }

    public void run() {
        int cantidadProducida = 0;
        try {
            while (!Thread.currentThread().isInterrupted()) {
                cantidadProducida++;
                // Añadir datos a la cola; put lanza InterruptedException si el hilo es interrumpido
                cola.put(new DatoProducido());
            }
        } catch (InterruptedException e) {
            // El hilo ha sido interrumpido: limpiar y salir
            Thread.currentThread().interrupt();  // Volver a establecer la bandera de interrupción
        }
        System.out.println("Producidos " + cantidadProducida + " objetos");
    }
}

// Clase Consumidor
