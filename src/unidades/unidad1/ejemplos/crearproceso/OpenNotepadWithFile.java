package unidades.unidad1.ejemplos.crearproceso;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class OpenNotepadWithFile extends JFrame {

    private JButton openNotepadButton = new JButton("Open Notepad with File");

    public OpenNotepadWithFile() {
        setTitle("Open Notepad with File Example");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Agregar un botón para abrir Notepad con un archivo
        openNotepadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Array de String para ejecutar Notepad con un archivo específico
                    String[] infoProceso = {"Notepad.exe", "notas.txt"};
                    Runtime.getRuntime().exec(infoProceso);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error opening Notepad with file");
                }
            }
        });

        add(openNotepadButton);
        setSize(300, 100);
        setVisible(true);
    }

    public static void main(String[] args) {
        new OpenNotepadWithFile();
    }
}
