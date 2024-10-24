package unidades.unidad2.ejemplos.hilomultihilo;

public class Main {

	public static void main(String[] args) {
		
		Proceso hilo1 = new Proceso("Hilo 1");
		Proceso hilo2 =  new Proceso("Hilo 2");
		
		hilo1.setMensaje("Este es el mensaje del hilo 1");
		hilo2.setMensaje("Mensaje hilo 2");
		
		hilo1.start();
		hilo2.start();

	}

}
