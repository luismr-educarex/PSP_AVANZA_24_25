package unidades.unidad2.ejemplos.executor;
public class Despegue implements Runnable {
	protected int cuentaAtras = 10; // Valor por defecto
	private static int contadorTareas = 0;
	private final int id = contadorTareas++;

	public Despegue() {
	}

	public Despegue(int cuentaRegresiva) {
		this.cuentaAtras = cuentaRegresiva;
	}

	public String estado() {
		return "#" + id + "(" + (cuentaAtras > 0 ? cuentaAtras : "¡Despegue!") + "), ";
	}

	public void run() {
		while (cuentaAtras-- > 0) {
			System.out.print(estado());
			Thread.yield();
		}
	}
}
