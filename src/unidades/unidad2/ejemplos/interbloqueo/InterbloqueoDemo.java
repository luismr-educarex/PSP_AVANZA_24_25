package unidades.unidad2.ejemplos.interbloqueo;
public class InterbloqueoDemo {

    // Objetos para actuar como recursos
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        // Hilo 1: Intenta bloquear lock1 y luego lock2
        Thread thread1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Hilo 1: Bloqueó lock1");

                // Intentar pausar el hilo para aumentar la probabilidad de interbloqueo
                try { Thread.sleep(100); } catch (InterruptedException e) {}

                System.out.println("Hilo 1: Intentando bloquear lock2");
                synchronized (lock2) {
                    System.out.println("Hilo 1: Bloqueó lock2");
                }
            }
        });

        // Hilo 2: Intenta bloquear lock2 y luego lock1
        Thread thread2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Hilo 2: Bloqueó lock2");

                // Intentar pausar el hilo para aumentar la probabilidad de interbloqueo
                try { Thread.sleep(100); } catch (InterruptedException e) {}

                System.out.println("Hilo 2: Intentando bloquear lock1");
                synchronized (lock1) {
                    System.out.println("Hilo 2: Bloqueó lock1");
                }
            }
        });

        // Iniciar ambos hilos
        thread1.start();
        thread2.start();
    }
}
