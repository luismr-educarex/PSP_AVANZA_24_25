package unidades.unidad2.ejemplos.cyclicbarrier.ejemplo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class TareaConBarreras implements Runnable {
    CyclicBarrier barrera1 = null;
    CyclicBarrier barrera2 = null;

    public TareaConBarreras(CyclicBarrier barrera1, CyclicBarrier barrera2) {
        this.barrera1 = barrera1;
        this.barrera2 = barrera2;
    }

    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " esperando en barrera 1");
            this.barrera1.await(); // Esperar en la primera barrera

            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " esperando en barrera 2");
            this.barrera2.await(); // Esperar en la segunda barrera

            System.out.println(Thread.currentThread().getName() + " ha terminado!");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}