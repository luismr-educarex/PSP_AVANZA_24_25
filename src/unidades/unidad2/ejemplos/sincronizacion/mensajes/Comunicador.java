package unidades.unidad2.ejemplos.sincronizacion.mensajes;
class Comunicador {
    private boolean mensajeEnviado = false;

    public synchronized void enviarMensaje(String mensaje) {
        while (mensajeEnviado) {
            try {
                wait();  // Espera hasta que el mensaje sea recibido
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Enviado: " + mensaje);
        mensajeEnviado = true;
        notify();  // Notifica al receptor que el mensaje ha sido enviado
    }

    public synchronized void recibirMensaje() {
        while (!mensajeEnviado) {
            try {
                wait();  // Espera hasta que haya un mensaje para recibir
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Recibido");
        mensajeEnviado = false;
        notify();  // Notifica al emisor que el mensaje ha sido recibido
    }
}