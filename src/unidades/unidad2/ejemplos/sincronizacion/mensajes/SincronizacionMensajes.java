package unidades.unidad2.ejemplos.sincronizacion.mensajes;
public class SincronizacionMensajes {
    public static void main(String[] args) {
        Comunicador comunicador = new Comunicador();

        Thread emisor = new Thread(() -> {
            String[] mensajes = {"Hola", "¿Cómo estás?", "Adiós"};
            for (String mensaje : mensajes) {
                comunicador.enviarMensaje(mensaje);
            }
        });

        Thread receptor = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                comunicador.recibirMensaje();
            }
        });

        emisor.start();
        receptor.start();
    }
}