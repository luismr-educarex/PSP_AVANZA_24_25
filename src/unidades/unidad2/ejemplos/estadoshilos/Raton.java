package unidades.unidad2.ejemplos.estadoshilos;

import java.util.ArrayList;

public class Raton implements Runnable {

	private String nombre;

	private int tiempoAlimentacion;

	public Raton(String nombre, int tiempoAlimentacion) {
		super();
		this.nombre = nombre;
		this.tiempoAlimentacion = tiempoAlimentacion;
	}

	public void comer() {

		try {
			System.out.printf("El ratón %s ha comenzado a alimentarse%n", nombre);
			Thread.sleep(tiempoAlimentacion * 1000);
			System.out.printf("El ratón %s ha terminado de alimentarse%n", nombre);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		this.comer();
	}

	public static void main(String[] args) {

		Raton pop = new Raton("Pop", 6);
		ArrayList<Thread.State> estadosHilo = new ArrayList();
		Thread h = new Thread(pop);
		estadosHilo.add(h.getState());
		h.start();
		while (h.getState() != Thread.State.TERMINATED) {
			if (!estadosHilo.contains(h.getState())) {
				estadosHilo.add(h.getState());
			}
		} // fin de while

		if (!estadosHilo.contains(h.getState())) {
			estadosHilo.add(h.getState());
		}

		for (Thread.State estado : estadosHilo) {
			System.out.println(estado);
		}
	}
}