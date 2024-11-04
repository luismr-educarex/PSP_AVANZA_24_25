package unidades.unidad2.ejemplos.executor.single;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import unidades.unidad2.ejemplos.executor.Despegue;

public class SingleThreadExecutor {
	public static void main(String[] args) {
		ExecutorService exec = Executors.newSingleThreadExecutor();
		for (int i = 0; i < 5; i++)
			exec.execute(new Despegue());
		exec.shutdown();
	}
}