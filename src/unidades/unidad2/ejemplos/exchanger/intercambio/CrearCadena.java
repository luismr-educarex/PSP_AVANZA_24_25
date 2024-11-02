package unidades.unidad2.ejemplos.exchanger.intercambio;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

// Clase que genera una cadena y la intercambia
class CrearCadena implements Runnable {
    Exchanger<String> intercambiador;
    String cadena;

    CrearCadena(Exchanger<String> intercambiador) {
        this.intercambiador = intercambiador;
        cadena = "";
        // Iniciar el hilo
        new Thread(this).start();
    }

    public void run() {
        char ch = 'A';
        try {
            for (int i = 0; i < 6; i++) {
                // Generar una cadena de 8 caracteres
                for (int j = 0; j < 8; j++) {
                    cadena += ch++;
                }

                if (i == 2) {
                    // Intercambiar el buffer con un límite de tiempo de 250 ms
                    cadena = intercambiador.exchange(cadena, 250, TimeUnit.MILLISECONDS);
                    continue;
                }

                // Intercambiar el buffer lleno con uno vacío
                cadena = intercambiador.exchange(cadena);
            }
        } catch (InterruptedException ex) {
            System.out.println("Interrupción: " + ex);
        } catch (TimeoutException t) {
            System.out.println("Se ha producido un tiempo de espera");
        }
    }
}