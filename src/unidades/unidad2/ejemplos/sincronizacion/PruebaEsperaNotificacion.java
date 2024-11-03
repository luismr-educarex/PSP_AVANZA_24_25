package unidades.unidad2.ejemplos.sincronizacion;
// Clase PruebaEsperaNotificacion (WaitNotifyTest)
public class PruebaEsperaNotificacion {

    public static void main(String[] args) {
        Mensaje msg = new Mensaje("procesar esto");
        
        Espera espera1 = new Espera(msg);
        new Thread(espera1, "espera1").start();
        
        Espera espera2 = new Espera(msg);
        new Thread(espera2, "espera2").start();
        
        Notificador notificador = new Notificador(msg);
        new Thread(notificador, "notificador").start();
        
        System.out.println("Todos los hilos han sido iniciados");
    }
}
