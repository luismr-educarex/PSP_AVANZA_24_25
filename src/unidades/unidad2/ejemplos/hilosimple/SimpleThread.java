package unidades.unidad2.ejemplos.hilosimple;

public class SimpleThread extends Thread {
	private int countDown = 5;
	private static int threadCount = 0;
	private int threadNumber = ++threadCount;

	public SimpleThread() {
		System.out.println("Creado " + threadNumber);
	}

	public void run() {
		while (true) {
			System.out.println("Hilo " + threadNumber + "(" + countDown + ")");
			if (--countDown == 0)
				return;
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++)
			new SimpleThread().start();
		System.out.println("Todos los hilos iniciados");
	}
} /// :~