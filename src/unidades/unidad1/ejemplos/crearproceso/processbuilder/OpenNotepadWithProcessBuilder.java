package unidades.unidad1.ejemplos.crearproceso.processbuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class OpenNotepadWithProcessBuilder extends JFrame {

    private JButton openNotepadButton = new JButton("Open Notepad");

    public OpenNotepadWithProcessBuilder() {
        setTitle("Open Notepad with ProcessBuilder");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Agregar un botón para abrir Notepad
        openNotepadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Usar ProcessBuilder para ejecutar Notepad
                    new ProcessBuilder("Notepad.exe").start();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error opening Notepad");
                }
            }
        });

        add(openNotepadButton);
        setSize(300, 100);
        setVisible(true);
    }

    public static void main(String[] args) {
        new OpenNotepadWithProcessBuilder();
    }
}
