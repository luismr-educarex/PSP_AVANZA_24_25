package unidades.unidad1.ejemplos.seccioncritica.semaforos;
public class PruebaSemaforo {
    public static void main(String[] args) {
        // Inicializamos el semáforo con 1 permiso (similar a un mutex)
        SemaforoPersonalizado semaforo = new SemaforoPersonalizado(1);

        try {
            System.out.println("Intentando adquirir el recurso...");
            semaforo.waitSemaforo();  // Adquirimos el recurso
            System.out.println("Recurso adquirido. Sección crítica.");

            // Simulamos una tarea en la sección crítica
            simularTarea();

            System.out.println("Liberando el recurso...");
            semaforo.signalSemaforo();  // Liberamos el recurso
            System.out.println("Recurso liberado.");

            System.out.println("Intentando adquirir el recurso de nuevo...");
            semaforo.waitSemaforo();  // Adquirimos el recurso nuevamente
            System.out.println("Recurso adquirido de nuevo. Sección crítica.");

            // Simulamos otra tarea en la sección crítica
            simularTarea();

            System.out.println("Liberando el recurso de nuevo...");
            semaforo.signalSemaforo();  // Liberamos el recurso de nuevo
            System.out.println("Recurso liberado.");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Método para simular una tarea (sección crítica)
    public static void simularTarea() {
        System.out.println("Ejecutando tarea en la sección crítica...");
        try {
            Thread.sleep(1000);  // Simulamos una tarea con un retardo de 1 segundo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
