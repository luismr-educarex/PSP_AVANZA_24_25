package unidades.unidad1.ejemplos.crearproceso;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CrearProcesosView extends JFrame {

    private JLabel statusMessageLabel;
    private JButton crearProcesoButton;

    public CrearProcesosView() {
        // Configuración básica del frame
        setTitle("Crear Procesos");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Crear un panel y componentes
        JPanel mainPanel = new JPanel(new BorderLayout());
        statusMessageLabel = new JLabel("Presiona el botón para crear un nuevo proceso.");
        crearProcesoButton = new JButton("Crear Nuevo Editor");

        // Acción del botón
        crearProcesoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearNuevoEditor();
            }
        });

        // Añadir componentes al panel
        mainPanel.add(statusMessageLabel, BorderLayout.CENTER);
        mainPanel.add(crearProcesoButton, BorderLayout.SOUTH);

        // Añadir el panel al frame
        this.getContentPane().add(mainPanel);
    }

    public void crearNuevoEditor() {
        Process nuevoProceso;
        try {
            String osName = System.getProperty("os.name");

            if (osName.toUpperCase().contains("WIN")) { // Windows
                nuevoProceso = Runtime.getRuntime().exec("java -jar C:\\PSP\\PROGRAMAS\\DocumentEditor.jar");
            } else { // Linux
                nuevoProceso = Runtime.getRuntime().exec("java -jar /ruta/a/tu/archivo/DocumentEditor.jar");
            }

            statusMessageLabel.setText("Proceso creado exitosamente.");
        } catch (Exception ex) {
            statusMessageLabel.setText("Error al crear el proceso: " + ex.getMessage());
        }
    }
}
