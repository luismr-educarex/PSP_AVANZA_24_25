package unidades.unidad2.ejemplos.planificador;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledTaskExample {

    public static void main(String[] args) {
        // Crear una instancia de ScheduledExecutorService
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Crear la tarea que queremos ejecutar
        Runnable task = () -> System.out.println("Ejecutando tarea programada: " + System.currentTimeMillis());

        // Programar la tarea para que se ejecute por primera vez después de 1 segundo,
        // y luego se repita cada 2 segundos
        scheduler.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS);

        // Agregar un tiempo de espera para detener el scheduler después de 10 segundos
        scheduler.schedule(() -> {
            System.out.println("Finalizando el scheduler...");
            scheduler.shutdown();
        }, 10, TimeUnit.SECONDS);
    }
}
