package unidades.unidad2.ejemplos.timers;
import java.util.Timer;
import java.util.TimerTask;

public class Temporizador {
    public static void main(String[] args) {
        // Tarea programada que imprime un mensaje y finaliza el programa.
        TimerTask tarea = new TimerTask() {
            public void run() {
                System.out.println("Alarma: La máquina está hirviendo...");
                System.exit(0); // Termina el programa
            }
        };

        // Configura un temporizador para ejecutar la tarea después de 2000 ms (2 segundos).
        Timer temporizador = new Timer();
        temporizador.schedule(tarea, 2000);

        // Bucle infinito que imprime "Hola mundo" cada 500 ms.
        for (;;) {
            System.out.println("Hola mundo");
            pausa(500);
        }
    }

    // Método para pausar el hilo actual durante el tiempo especificado en milisegundos.
    private static void pausa(int tiempo) {
        try {
            Thread.sleep(tiempo);
        } catch (Exception ex) {
            // Ignora cualquier excepción
        }
    }
}
