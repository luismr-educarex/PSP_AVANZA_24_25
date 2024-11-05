package unidades.unidad2.ejemplos.timers;
import java.util.Timer;
import java.util.TimerTask;

public class Temporizador2 {
    public static void main(String[] args) {
        // Tarea programada que imprime un mensaje de alarma
        TimerTask tarea = new TimerTask() {
            public void run() {
                System.out.println("Alarma: La máquina está hirviendo...");
            }
        };

        // Configura un temporizador para ejecutar la tarea cada segundo después de un retraso inicial de 500 ms.
        Timer temporizador = new Timer();
        temporizador.schedule(tarea, 500, 1000);

        // Bucle que imprime "Hola mundo" diez veces, con una pausa de 300 ms entre cada impresión.
        for (int i = 0; i < 10; ++i) {
            System.out.println("Hola mundo");
            pausa(300);
        }

        // Cancela el temporizador una vez que el bucle ha terminado.
        temporizador.cancel();
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
