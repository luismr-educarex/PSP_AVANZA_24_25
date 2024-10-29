package unidades.unidad2.ejemplos.prioridades;

public class Hilo extends Thread {

    /**
     * Constructor por defecto, hereda la prioridad del hilo padre.
     */
    public Hilo() {
        // No se realiza ninguna acción especial aquí
    }

    /**
     * Constructor personalizado que permite establecer la prioridad indicada.
     */
    public Hilo(int prioridad) {
        this.setPriority(prioridad);
    }

    /**
     * Ejecuta una tarea pesada.
     */
    @Override
    public void run() {
        // Usa StringBuilder para evitar concatenación ineficiente
        StringBuilder strCadena = new StringBuilder();

        // Agrega 20000 caracteres a una cadena vacía
        for (int i = 0; i < 20000; ++i) {
            strCadena.append("A");
            Thread.yield(); // sugiere al planificador Java que puede seleccionar otro hilo
        }

        System.out.println("Hilo de prioridad " + this.getPriority() + " termina ahora");
    }
}
