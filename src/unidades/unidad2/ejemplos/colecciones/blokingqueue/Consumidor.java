package unidades.unidad2.ejemplos.colecciones.blokingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import unidades.unidad2.ejemplos.colecciones.blokingqueue.EjemploProductorConsumidor.DatoProducido;

public class Consumidor implements Runnable {
    private final BlockingQueue<DatoProducido> cola;

    public Consumidor(BlockingQueue<DatoProducido> cola) {
        this.cola = cola;
    }

    public void run() {
        int cantidadConsumida = 0;
        try {
            while (!Thread.currentThread().isInterrupted() || !cola.isEmpty()) {
                // poll lanza InterruptedException si el hilo es interrumpido y espera un tiempo específico
                DatoProducido dato = cola.poll(10, TimeUnit.MILLISECONDS);
                if (dato != null) { // Procesar solo si se ha recuperado un objeto
                    cantidadConsumida++;
                    // Aquí podrías agregar el código para procesar los datos
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();  // Volver a establecer la bandera de interrupción
        }
        System.out.println("Consumidos " + cantidadConsumida + " objetos");
    }
}

// Clase de ejemplo Productor-Consumidor
