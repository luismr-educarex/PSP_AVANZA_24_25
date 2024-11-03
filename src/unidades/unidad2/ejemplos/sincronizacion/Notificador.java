package unidades.unidad2.ejemplos.sincronizacion;
// Clase Notificador
public class Notificador implements Runnable {

    private Mensaje msg;
    
    public Notificador(Mensaje msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        String nombre = Thread.currentThread().getName();
        System.out.println(nombre + " iniciado");
        try {
            Thread.sleep(1000);
            synchronized (msg) {
                msg.setMsg(nombre + " Notificador ha terminado su trabajo");
                msg.notify();
                // msg.notifyAll(); // Puede usarse para notificar a todos los hilos en espera
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
