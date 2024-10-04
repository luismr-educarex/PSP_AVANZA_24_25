package unidades.unidad1.ejemplos.crearproceso;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class DocumentEditor extends JFrame {

    private JTextArea textArea;
    private JFileChooser fileChooser;

    public DocumentEditor() {
        // Configurar la ventana principal
        setTitle("Editor de Texto");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear un área de texto
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        // Crear un menú
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("Archivo");
        JMenuItem menuItemOpen = new JMenuItem("Abrir");
        JMenuItem menuItemSave = new JMenuItem("Guardar");
        JMenuItem menuItemExit = new JMenuItem("Salir");

        menuFile.add(menuItemOpen);
        menuFile.add(menuItemSave);
        menuFile.addSeparator();
        menuFile.add(menuItemExit);
        menuBar.add(menuFile);
        setJMenuBar(menuBar);

        // Inicializar el selector de archivos
        fileChooser = new JFileChooser();

        // Acción para abrir un archivo
        menuItemOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnValue = fileChooser.showOpenDialog(DocumentEditor.this);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    openFile(selectedFile);
                }
            }
        });

        // Acción para guardar un archivo
        menuItemSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnValue = fileChooser.showSaveDialog(DocumentEditor.this);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    saveFile(fileToSave);
                }
            }
        });

        // Acción para salir de la aplicación
        menuItemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    // Método para abrir un archivo y mostrar su contenido en el área de texto
    private void openFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            textArea.read(reader, null);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al abrir el archivo: " + e.getMessage());
        }
    }

    // Método para guardar el contenido del área de texto en un archivo
    private void saveFile(File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            textArea.write(writer);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar el archivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DocumentEditor().setVisible(true);
            }
        });
    }
}
