package unidades.unidad2.ejemplos.creacionhilo;
 
public class Saludo extends Thread {
	//clase que implementa a Runnable
	    public void run() {
	    //se redefine el m�todo run()con el c�digo asociado al hilo
	        System.out.println("Saludo desde un hilo creado con Runnable!");
	    }
	    public static void main(String args[]) {
	    	Saludo  miRunnable=new Saludo();
	       //se crea un objeto  Saludo
	       Thread hilo1= new Thread(miRunnable);
	       //se crea un objeto Thread (el hilo hilo1) pasando como argumento
	      // al constructor un objeto Saludo
	        hilo1.start();
	       //se invoca al m�todo start() del hilo hilo1
	    }
  } 