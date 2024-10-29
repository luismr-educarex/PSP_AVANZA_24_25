package unidades.unidad2.ejemplos.creacionhilo;
 public class Saludo2 extends Thread {
 //clase  que extiende a Thread
    public void run() {
    // se redefine el método run() con el código asociado al hilo
        System.out.println("Saludo desde un hilo extendiendo thread!");
    }
    
    
    public static void main(String args[]) {
        Saludo2 hilo1=new Saludo2();
        //se crea un objeto Thread, el hilo hilo1
        hilo1.start();
        //invoca a start() y pone en marcha el hilo hilo1
    }
  } 