package unidades.unidad1.ejemplos.crearproceso;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class OpenNotepadWaitFor extends JFrame {

    private JButton openNotepadButton = new JButton("Open Notepad and Wait");

    public OpenNotepadWaitFor() {
        setTitle("Open Notepad and Wait Example");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Agregar un botón para abrir Notepad y esperar a que finalice
        openNotepadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Array de String para ejecutar Notepad con un archivo específico
                    String[] infoProceso = {"Notepad.exe", "notas.txt"};
                    // Ejecutar el proceso
                    Process proceso = Runtime.getRuntime().exec(infoProceso);
                    
                    // Esperar a que el proceso termine
                    int codigoRetorno = proceso.waitFor();

                    // Mostrar el código de retorno cuando el proceso termina
                    System.out.println("Fin de la ejecución: " + codigoRetorno);

                } catch (IOException | InterruptedException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error during Notepad execution");
                }
            }
        });

        add(openNotepadButton);
        setSize(300, 100);
        setVisible(true);
    }

    public static void main(String[] args) {
        new OpenNotepadWaitFor();
    }
}
