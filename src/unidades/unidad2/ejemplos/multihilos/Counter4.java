package unidades.unidad2.ejemplos.multihilos;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Counter4 extends JFrame {
    private JButton start = new JButton("Start");
    private boolean started = false;
    private Ticker[] s;
    private int size = 12;

    // Clase interna Ticker
    class Ticker extends Thread {
        private JButton b = new JButton("Toggle");
        private JTextField t = new JTextField(10);
        private int count = 0;
        private boolean runFlag = true;

        public Ticker() {
            b.addActionListener(new ToggleL());
            JPanel p = new JPanel();
            p.add(t);
            p.add(b);
            getContentPane().add(p);
        }

        // Clase interna ToggleL
        class ToggleL implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                runFlag = !runFlag;
            }
        }

        public void run() {
            while (true) {
                if (runFlag)
                    t.setText(Integer.toString(count++));
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    System.err.println("Interrumpido");
                }
            }
        }
    }

    // Clase interna StartL
    class StartL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (!started) {
                started = true;
                for (int i = 0; i < s.length; i++)
                    s[i].start();
            }
        }
    }

    // Constructor de la aplicación
    public Counter4(int size) {
        this.size = size;
        setLayout(new FlowLayout());
        s = new Ticker[size];
        for (int i = 0; i < s.length; i++)
            s[i] = new Ticker();
        start.addActionListener(new StartL());
        add(start);
        setSize(300, size * 50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        int size = 12;
        if (args.length > 0) {
            try {
                size = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Tamaño inválido, usando tamaño por defecto de 12.");
            }
        }
        Counter4 app = new Counter4(size);
        app.setVisible(true);
    }
}
