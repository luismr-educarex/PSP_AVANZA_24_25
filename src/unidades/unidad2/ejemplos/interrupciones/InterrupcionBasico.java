package unidades.unidad2.ejemplos.interrupciones;
public class InterrupcionBasico extends Thread{
	
	public void run() {
		int contador = 0;
		while(true) {
			contador++;
			try {
				System.out.println(contador);
				if(contador==3) {
					this.interrupt();
				}
				Thread.sleep(1000);
			}catch (InterruptedException ie) {
				return;
			}
			
		}
	}
	
	public static void main(String[] args ) {
		new InterrupcionBasico().start();
	}

}