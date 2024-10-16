package unidades.unidad1.ejemplos.seccioncritica.semaforos;
// Clase que simula el acceso a una sección crítica usando el semáforo personalizado
public class EjemploSemaforoPersonalizado {
    // Creamos un semáforo personalizado con un valor inicial de 1 (mutex)
    private static SemaforoPersonalizado semaforo = new SemaforoPersonalizado(1);

    public static void main(String[] args) {
        // Creamos dos hilos que intentarán acceder a una sección crítica
        Thread hilo1 = new Thread(new Tarea("Hilo 1"));
        Thread hilo2 = new Thread(new Tarea("Hilo 2"));

        hilo1.start();
        hilo2.start();
    }

    // Clase que representa una tarea que intentará acceder a la sección crítica
    static class Tarea implements Runnable {
        private String nombre;

        public Tarea(String nombre) {
            this.nombre = nombre;
        }

        @Override
        public void run() {
            try {
                System.out.println(nombre + " está intentando acceder a la sección crítica...");
                semaforo.waitSemaforo();  // Operación equivalente a wait
                System.out.println(nombre + " ha entrado en la sección crítica.");

                // Simulamos que el hilo está realizando una tarea
                Thread.sleep(2000);

                System.out.println(nombre + " ha salido de la sección crítica.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaforo.signalSemaforo();  // Operación equivalente a signal
            }
        }
    }
}