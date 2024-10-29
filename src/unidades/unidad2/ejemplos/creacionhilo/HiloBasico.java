package unidades.unidad2.ejemplos.creacionhilo;

public class HiloBasico extends Thread{
	
	private int id;
	
	public HiloBasico(int id) {
		this.id = id;
		
	}
	
	public void run() {
		while (true) {
			System.out.println("Procesando hilo "+ id);
			try {
				Thread.sleep(1000);
			
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String []args) {
		for(int i=0;i<10;i++) {
			new Thread(new HiloBasico(i)).start();
		}
	}
}