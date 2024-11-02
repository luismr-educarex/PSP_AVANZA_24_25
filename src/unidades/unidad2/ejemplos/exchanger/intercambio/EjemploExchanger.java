package unidades.unidad2.ejemplos.exchanger.intercambio;
import java.util.concurrent.Exchanger;

public class EjemploExchanger {
    public static void main(String[] args) {
        // Crear un Exchanger para intercambiar cadenas
        Exchanger<String> intercambiador = new Exchanger<>();

        // Iniciar los hilos que usan el Exchanger
        new UsarCadena(intercambiador);
        new CrearCadena(intercambiador);
    }
}