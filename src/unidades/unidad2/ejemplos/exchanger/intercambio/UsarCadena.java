package unidades.unidad2.ejemplos.exchanger.intercambio;

import java.util.concurrent.Exchanger;

// Clase que recibe la cadena y la usa (imprime)
class UsarCadena implements Runnable {
    Exchanger<String> intercambiador;
    String cadena;

    UsarCadena(Exchanger<String> intercambiador) {
        this.intercambiador = intercambiador;
        // Iniciar el hilo
        new Thread(this).start();
    }

    public void run() {
        try {
            for (int i = 0; i < 6; i++) {
                if (i == 2) {
                    // Simular un tiempo de espera de 500 ms en el tercer intercambio
                    Thread.sleep(500);
                    continue;
                }

                // Intercambiar un buffer vacío por el buffer lleno
                cadena = intercambiador.exchange("");
                System.out.println("La cadena es: " + cadena);
            }
        } catch (InterruptedException ex) {
            System.out.println("Interrupción: " + ex);
        }
    }
}