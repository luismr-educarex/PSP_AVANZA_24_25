package unidades.unidad2.ejemplos.inanicion;
public class InanicionDemo {

    private static final Object recurso = new Object();

    public static void main(String[] args) {
        // Hilo de baja prioridad que experimentará inanición
        Thread hiloBajaPrioridad = new Thread(new Tarea(), "Hilo de Baja Prioridad");
        hiloBajaPrioridad.setPriority(Thread.MIN_PRIORITY);

        // Hilos de alta prioridad que acaparan el recurso
        Thread hiloAltaPrioridad1 = new Thread(new Tarea(), "Hilo de Alta Prioridad 1");
        hiloAltaPrioridad1.setPriority(Thread.MAX_PRIORITY);

        Thread hiloAltaPrioridad2 = new Thread(new Tarea(), "Hilo de Alta Prioridad 2");
        hiloAltaPrioridad2.setPriority(Thread.MAX_PRIORITY);

        // Iniciar los hilos
        hiloBajaPrioridad.start();
        hiloAltaPrioridad1.start();
        hiloAltaPrioridad2.start();
    }

    static class Tarea implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (recurso) {
                    System.out.println(Thread.currentThread().getName() + " obtuvo el recurso.");

                    // Simula tiempo de uso del recurso
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                    System.out.println(Thread.currentThread().getName() + " liberó el recurso.");
                }

                // Permitir que otros hilos intenten acceder al recurso
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
