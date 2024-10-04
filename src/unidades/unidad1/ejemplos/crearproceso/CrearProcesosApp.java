package unidades.unidad1.ejemplos.crearproceso;

public class CrearProcesosApp {

    public static void main(String[] args) {
        // Crear y mostrar la vista principal
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CrearProcesosView view = new CrearProcesosView();
                view.setVisible(true);
            }
        });
    }
}
