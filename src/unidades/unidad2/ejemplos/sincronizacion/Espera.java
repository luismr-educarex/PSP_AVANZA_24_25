package unidades.unidad2.ejemplos.sincronizacion;
// Clase Espera (Waiter)
public class Espera implements Runnable {
    
    private Mensaje msg;
    
    public Espera(Mensaje m) {
        this.msg = m;
    }

    @Override
    public void run() {
        String nombre = Thread.currentThread().getName();
        synchronized (msg) {
            try {
                System.out.println(nombre + " esperando a ser notificado en el momento: " + System.currentTimeMillis());
                msg.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(nombre + " hilo de espera recibió la notificación en el momento: " + System.currentTimeMillis());
            // Procesar el mensaje ahora
            System.out.println(nombre + " procesado: " + msg.getMsg());
        }
    }
}
