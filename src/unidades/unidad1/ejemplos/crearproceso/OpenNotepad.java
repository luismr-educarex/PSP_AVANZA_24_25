package unidades.unidad1.ejemplos.crearproceso;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class OpenNotepad extends JFrame {

    private JButton openNotepadButton = new JButton("Open Notepad");

    public OpenNotepad() {
        setTitle("Open Notepad Example");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Agregar un botón para abrir Notepad
        openNotepadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Ejecutar Notepad usando Runtime
                    Runtime.getRuntime().exec("Notepad.exe notas.txt");
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
        new OpenNotepad();
    }
}
