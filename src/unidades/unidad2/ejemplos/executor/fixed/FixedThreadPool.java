package unidades.unidad2.ejemplos.executor.fixed;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import unidades.unidad2.ejemplos.executor.Despegue;

public class FixedThreadPool {
	public static void main(String[] args) {
		// Constructor argument is number of threads:
		ExecutorService exec = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 5; i++)
			exec.execute(new Despegue());
		exec.shutdown();
	}
}