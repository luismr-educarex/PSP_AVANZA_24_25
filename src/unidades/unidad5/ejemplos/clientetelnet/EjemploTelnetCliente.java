package unidades.unidad5.ejemplos.clientetelnet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.StringTokenizer;

import org.apache.commons.net.telnet.EchoOptionHandler;
import org.apache.commons.net.telnet.InvalidTelnetOptionException;
import org.apache.commons.net.telnet.SimpleOptionHandler;
import org.apache.commons.net.telnet.SuppressGAOptionHandler;
import org.apache.commons.net.telnet.TelnetClient;
import org.apache.commons.net.telnet.TelnetNotificationHandler;
import org.apache.commons.net.telnet.TerminalTypeOptionHandler;

/**
 * Este es un ejemplo simple del uso de TelnetClient. 
 * Se usa un manejador de opciones externo (SimpleTelnetOptionHandler).
 * La configuración inicial solicitada por TelnetClient será:
 * WILL ECHO, WILL SUPPRESS-GA, DO SUPPRESS-GA.
 * Se usará el tipo de terminal VT100.
 *
 * También se demuestra el uso de sendAYT(), getLocalOptionState() y getRemoteOptionState().
 * Al estar conectado, escribe "AYT" para enviar un comando AYT al servidor y ver la respuesta.
 * Escribe "OPT" para ver un informe del estado de las primeras 25 opciones.
 */
public class EjemploTelnetCliente implements Runnable, TelnetNotificationHandler {
    private static TelnetClient clienteTelnet;

    /**
     * Método principal de EjemploTelnetCliente.
     *
     * @param args parámetros de entrada
     * @throws Exception en caso de error
     */
    public static void main(final String[] args) throws Exception {
        FileOutputStream archivoEspia = null;

        if (args.length < 1) {
            System.err.println("Uso: EjemploTelnetCliente <ip-remota> [<puerto-remoto>]");
            System.exit(1);
        }

        final String ipRemota = args[0];
        final int puertoRemoto = (args.length > 1) ? Integer.parseInt(args[1]) : 23;

        try {
            archivoEspia = new FileOutputStream("espia.log", true);
        } catch (final IOException e) {
            System.err.println("Error al abrir el archivo de espionaje: " + e.getMessage());
        }

        clienteTelnet = new TelnetClient();

        // Configuración de opciones de Telnet
        final TerminalTypeOptionHandler terminalOpt = new TerminalTypeOptionHandler("VT100", false, false, true, false);
        final EchoOptionHandler echoOpt = new EchoOptionHandler(true, false, true, false);
        final SuppressGAOptionHandler gaOpt = new SuppressGAOptionHandler(true, true, true, true);

        try {
            clienteTelnet.addOptionHandler(terminalOpt);
            clienteTelnet.addOptionHandler(echoOpt);
            clienteTelnet.addOptionHandler(gaOpt);
        } catch (final InvalidTelnetOptionException e) {
            System.err.println("Error al registrar los manejadores de opciones: " + e.getMessage());
        }

        while (true) {
            boolean terminar = false;
            try {
                clienteTelnet.connect(ipRemota, puertoRemoto);

                final Thread lector = new Thread(new EjemploTelnetCliente());
                clienteTelnet.registerNotifHandler(new EjemploTelnetCliente());

                System.out.println("Ejemplo de TelnetClient");
                System.out.println("Escribe AYT para enviar un comando AYT");
                System.out.println("Escribe OPT para ver el estado de opciones (0-24)");
                System.out.println("Escribe REGISTRAR para registrar un nuevo manejador de opciones");
                System.out.println("Escribe DESREGISTRAR para eliminar un manejador de opciones");
                System.out.println("Escribe ESPIA para registrar la conexión espía (puerto 3333)");
                System.out.println("Escribe NOESPIA para detener la conexión espía");
                System.out.println("Escribe ^[A-Z] para enviar un carácter de control; usa ^^ para enviar ^");

                lector.start();
                final OutputStream salida = clienteTelnet.getOutputStream();
                final byte[] buffer = new byte[1024];
                int cantidadLeida = 0;

                do {
                    try {
                        cantidadLeida = System.in.read(buffer);
                        if (cantidadLeida > 0) {
                            final Charset charset = Charset.defaultCharset();
                            final String linea = new String(buffer, 0, cantidadLeida, charset);

                            if (linea.startsWith("AYT")) {
                                try {
                                    System.out.println("Enviando AYT");
                                    System.out.println("Respuesta AYT: " + clienteTelnet.sendAYT(Duration.ofSeconds(5)));
                                } catch (final IOException e) {
                                    System.err.println("Error al recibir la respuesta AYT: " + e.getMessage());
                                }
                            } else if (linea.startsWith("OPT")) {
                                System.out.println("Estado de opciones:");
                                for (int i = 0; i < 25; i++) {
                                    System.out.println("Opción Local " + i + ": " + clienteTelnet.getLocalOptionState(i) +
                                            " Opción Remota " + i + ": " + clienteTelnet.getRemoteOptionState(i));
                                }
                            } else if (linea.startsWith("REGISTRAR")) {
                                final StringTokenizer st = new StringTokenizer(new String(buffer, charset));
                                try {
                                    st.nextToken();
                                    final int codigoOpcion = Integer.parseInt(st.nextToken());
                                    final boolean localInicial = Boolean.parseBoolean(st.nextToken());
                                    final boolean remotoInicial = Boolean.parseBoolean(st.nextToken());
                                    final boolean aceptarLocal = Boolean.parseBoolean(st.nextToken());
                                    final boolean aceptarRemoto = Boolean.parseBoolean(st.nextToken());
                                    final SimpleOptionHandler manejador = new SimpleOptionHandler(codigoOpcion, localInicial, remotoInicial, aceptarLocal, aceptarRemoto);
                                    clienteTelnet.addOptionHandler(manejador);
                                } catch (final Exception e) {
                                    System.err.println("Error al registrar la opción: " + e.getMessage());
                                }
                            } else if (linea.startsWith("DESREGISTRAR")) {
                                final StringTokenizer st = new StringTokenizer(new String(buffer, charset));
                                try {
                                    st.nextToken();
                                    final int codigoOpcion = Integer.parseInt(st.nextToken());
                                    clienteTelnet.deleteOptionHandler(codigoOpcion);
                                } catch (final Exception e) {
                                    System.err.println("Error al desregistrar la opción: " + e.getMessage());
                                }
                            } else if (linea.startsWith("ESPIA")) {
                                clienteTelnet.registerSpyStream(archivoEspia);
                            } else if (linea.startsWith("NOESPIA")) {
                                clienteTelnet.stopSpyStream();
                            } else {
                                try {
                                    salida.write(buffer, 0, cantidadLeida);
                                    salida.flush();
                                } catch (final IOException e) {
                                    terminar = true;
                                }
                            }
                        }
                    } catch (final IOException e) {
                        System.err.println("Error al leer el teclado: " + e.getMessage());
                        terminar = true;
                    }
                } while (cantidadLeida > 0 && !terminar);

                try {
                    clienteTelnet.disconnect();
                } catch (final IOException e) {
                    System.err.println("Error al desconectar: " + e.getMessage());
                }
            } catch (final IOException e) {
                System.err.println("Error al conectar: " + e.getMessage());
                System.exit(1);
            }
        }
    }

    /**
     * Método de callback cuando TelnetClient recibe un comando de negociación de opciones.
     */
    @Override
    public void receivedNegotiation(final int codigoNegociacion, final int codigoOpcion) {
        String comando;
        switch (codigoNegociacion) {
            case TelnetNotificationHandler.RECEIVED_DO -> comando = "DO";
            case TelnetNotificationHandler.RECEIVED_DONT -> comando = "DONT";
            case TelnetNotificationHandler.RECEIVED_WILL -> comando = "WILL";
            case TelnetNotificationHandler.RECEIVED_WONT -> comando = "WONT";
            default -> comando = Integer.toString(codigoNegociacion);
        }
        System.out.println("Recibido " + comando + " para la opción " + codigoOpcion);
    }

    /**
     * Hilo lector: lee datos de la conexión Telnet y los muestra en pantalla.
     */
    @Override
    public void run() {
        final InputStream entrada = clienteTelnet.getInputStream();
        try {
            final byte[] buffer = new byte[1024];
            int cantidadLeida;
            do {
                cantidadLeida = entrada.read(buffer);
                if (cantidadLeida > 0) {
                    System.out.print(new String(buffer, 0, cantidadLeida, Charset.defaultCharset()));
                }
            } while (cantidadLeida >= 0);
        } catch (final IOException e) {
            System.err.println("Error al leer el socket: " + e.getMessage());
        }
    }
}
