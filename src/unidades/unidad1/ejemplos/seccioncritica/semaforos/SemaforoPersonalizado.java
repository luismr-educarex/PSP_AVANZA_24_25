package unidades.unidad1.ejemplos.seccioncritica.semaforos;


public class SemaforoPersonalizado {
    private int valor;

    // Constructor para inicializar el semáforo con un valor inicial
    public SemaforoPersonalizado(int valorInicial) {
        this.valor = valorInicial;
    }

    // Implementación de la operación 'wait' (equivalente a acquire)
    public synchronized void waitSemaforo() throws InterruptedException {
        while (valor <= 0) {
            // Si el valor del semáforo es 0 o negativo, espera
            wait();
        }
        valor--;  // Decrementamos el valor del semáforo (adquiere recurso)
    }

    // Implementación de la operación 'signal' (equivalente a release)
    public synchronized void signalSemaforo() {
        valor++;  // Incrementamos el valor del semáforo (libera recurso)
        notify();  // Despertamos a un hilo que esté esperando en 'waitSemaforo'
    }
}


