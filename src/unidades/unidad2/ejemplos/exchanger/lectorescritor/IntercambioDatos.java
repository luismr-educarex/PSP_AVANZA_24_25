package unidades.unidad2.ejemplos.exchanger.lectorescritor;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Exchanger;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.util.concurrent.CompletableFuture.runAsync;

public class IntercambioDatos {
    private static final int TAMANIO_BUFFER = 10;
    private static final int TOTAL_NUMEROS = 100;

    public static void main(String[] args) {

        // Crear exchangers para intercambiar datos entre los hilos
        Exchanger<Queue<String>> lectorExchanger = new Exchanger<>();
        Exchanger<Queue<String>> escritorExchanger = new Exchanger<>();

        // Variable para indicar que la generación ha terminado
        AtomicBoolean generacionCompleta = new AtomicBoolean(false);

        // Lector: Genera 100 datos y los envía al procesador
        Runnable lector = () -> {
            Queue<String> bufferLector = new ConcurrentLinkedQueue<>();
            int contador = 0;
            while (contador < TOTAL_NUMEROS) {
                // Generar un dato aleatorio y añadirlo al buffer
                bufferLector.add(UUID.randomUUID().toString());
                contador++;

                // Cuando el buffer alcanza el tamaño, lo intercambia con el procesador
                if (bufferLector.size() >= TAMANIO_BUFFER) {
                    try {
                        bufferLector = lectorExchanger.exchange(bufferLector);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }

            // Intercambiar el buffer restante y marcar que la generación ha terminado
            try {
                lectorExchanger.exchange(bufferLector);
                generacionCompleta.set(true);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // Procesador: Recibe datos del lector, los procesa y los envía al escritor
        Runnable procesador = () -> {
            Queue<String> bufferProcesador = new ConcurrentLinkedQueue<>();
            Queue<String> bufferEscritor = new ConcurrentLinkedQueue<>();
            try {
                bufferProcesador = lectorExchanger.exchange(bufferProcesador); // Inicializar buffer
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            while (!generacionCompleta.get() || !bufferProcesador.isEmpty()) {
                bufferEscritor.add(bufferProcesador.poll()); // Procesar un elemento

                // Cuando el buffer del procesador está vacío, intercambia con el lector
                if (bufferProcesador.isEmpty() && !generacionCompleta.get()) {
                    try {
                        bufferProcesador = lectorExchanger.exchange(bufferProcesador);
                        bufferEscritor = escritorExchanger.exchange(bufferEscritor);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }

            // Intercambiar el buffer final con el escritor
            try {
                escritorExchanger.exchange(bufferEscritor);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // Escritor: Recibe datos del procesador y los muestra
        Runnable escritor = () -> {
            Queue<String> bufferEscritor = new ConcurrentLinkedQueue<>();
            try {
                bufferEscritor = escritorExchanger.exchange(bufferEscritor); // Inicializar buffer
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            while (!generacionCompleta.get() || !bufferEscritor.isEmpty()) {
                String data = bufferEscritor.poll();
                if (data != null) {
                    System.out.println(data); // Imprimir un elemento
                }

                // Cuando el buffer está vacío, intercambia con el procesador
                if (bufferEscritor.isEmpty() && !generacionCompleta.get()) {
                    try {
                        bufferEscritor = escritorExchanger.exchange(bufferEscritor);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        };

        // Ejecutar los tres hilos simultáneamente
        CompletableFuture.allOf(
                runAsync(lector),
                runAsync(procesador),
                runAsync(escritor)
        ).join();
    }
}
