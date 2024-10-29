package unidades.unidad2.ejemplos.prioridades;

public class Programa {

    public static void main(String[] args) {
        int contador = 5;

        // Vectores para hilos de distintas prioridades
        Thread[] hiloMIN = new Thread[contador];
        Thread[] hiloNORM = new Thread[contador];
        Thread[] hiloMAX = new Thread[contador];

        // Crea los hilos de prioridad mínima
        for (int i = 0; i < contador; i++) {
            hiloMIN[i] = new Hilo(Thread.MIN_PRIORITY);
        }

        // Crea los hilos de prioridad normal 
        for (int i = 0; i < contador; i++) {
            hiloNORM[i] = new Hilo(Thread.NORM_PRIORITY);
        }

        // Crea los hilos de máxima prioridad
        for (int i = 0; i < contador; i++) {
            hiloMAX[i] = new Hilo(Thread.MAX_PRIORITY);
        }

        System.out.println("Hilos en proceso, espera......\nLos de mayor "
                + "prioridad tienden a terminar antes...\n");

        // Inicia los hilos
        for (int i = 0; i < contador; i++) {
            hiloMIN[i].start();
            hiloNORM[i].start();
            hiloMAX[i].start();
        }
    }
}
