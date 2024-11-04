package unidades.unidad2.ejemplos.executor.sheduled;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import unidades.unidad2.ejemplos.executor.Despegue;

public class ScheduledThreadPoolExample {
    public static void main(String[] args) {
        // Crea un ScheduledExecutorService con 5 hilos:
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(5);
        // Programa cada tarea para que se ejecute después de un retraso inicial.
        for (int i = 0; i < 5; i++) {
            exec.schedule(new Despegue(), i * 2, TimeUnit.SECONDS);
        }
        // Finaliza el servicio después de un tiempo para permitir que las tareas se completen.
        exec.shutdown();
    }
}