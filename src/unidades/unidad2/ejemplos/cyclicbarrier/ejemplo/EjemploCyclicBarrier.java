package unidades.unidad2.ejemplos.cyclicbarrier.ejemplo;

import java.util.concurrent.CyclicBarrier;

public class EjemploCyclicBarrier {

    public static void main(String[] args) {
        // Definir acciones que se ejecutan al alcanzar cada barrera
        Runnable accionBarrera1 = new Runnable() {
            public void run() {
                System.out.println("AccionBarrera 1 ejecutada");
            }
        };
        
        Runnable accionBarrera2 = new Runnable() {
            public void run() {
                System.out.println("AccionBarrera 2 ejecutada");
            }
        };

        // Crear las barreras con sus respectivas acciones
        CyclicBarrier barrera1 = new CyclicBarrier(2, accionBarrera1);
        CyclicBarrier barrera2 = new CyclicBarrier(2, accionBarrera2);

        // Crear las instancias de CyclicBarrierRunnable
        TareaConBarreras tarea1 = new TareaConBarreras(barrera1, barrera2);
        TareaConBarreras tarea2 = new TareaConBarreras(barrera1, barrera2);

        // Iniciar los hilos
        new Thread(tarea1).start();
        new Thread(tarea2).start();
    }
}