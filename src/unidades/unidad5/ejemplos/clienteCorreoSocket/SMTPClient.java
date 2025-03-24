package unidades.unidad5.ejemplos.clienteCorreoSocket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Base64;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class SMTPClient {
    public static void main(String[] args) {
        String smtpServer = "smtp.gmail.com"; // Cambia esto si usas otro servidor
        int smtpPort = 587; // Usa 465 para SSL o 25 para no seguro
        String username = "uuuuuu"; // Cambia esto por tu correo
        String password = "xxxxxxxxx"; // Cambia esto por tu contraseña de aplicación proporcionada por Google.
        String from = "fffffff";
        String to = "tttttttt";
        String subject = "Prueba de Cliente SMTP en Java";
        String body = "Este es un mensaje de prueba enviado con un cliente SMTP basado en sockets en Java.";

        try {
        	// Conectar con el servidor SMTP
            Socket socket = new Socket(smtpServer, smtpPort);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            // Leer el mensaje de bienvenida del servidor
            System.out.println("S: " + reader.readLine());

            // Enviar EHLO
            writer.println("EHLO example.com");
            readResponse(reader);

            // Iniciar STARTTLS para usar una conexión segura
            writer.println("STARTTLS");
            readResponse(reader);

            // Convertir la conexión en una conexión segura con TLS
            SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket(socket, smtpServer, smtpPort, true);
            reader = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
            writer = new PrintWriter(sslSocket.getOutputStream(), true);

            // Enviar EHLO nuevamente después de la conexión segura
            writer.println("EHLO example.com");
            readResponse(reader);

            // Autenticación
            writer.println("AUTH LOGIN");
            readResponse(reader);

            // Enviar usuario codificado en Base64
            writer.println(Base64.getEncoder().encodeToString(username.getBytes()));
            readResponse(reader);

            // Enviar contraseña codificada en Base64
            writer.println(Base64.getEncoder().encodeToString(password.getBytes()));
            readResponse(reader);

            // Enviar comando MAIL FROM
            writer.println("MAIL FROM:<" + from + ">");
            readResponse(reader);

            // Enviar comando RCPT TO
            writer.println("RCPT TO:<" + to + ">");
            readResponse(reader);

            // Enviar comando DATA
            writer.println("DATA");
            readResponse(reader);

            // Enviar el cuerpo del mensaje
            writer.println("Subject: " + subject);
            writer.println("From: " + from);
            writer.println("To: " + to);
            writer.println("");
            writer.println(body);
            writer.println(".");
            readResponse(reader);

            // Cerrar sesión con el servidor SMTP
            writer.println("QUIT");
            readResponse(reader);

            // Cerrar socket
            sslSocket.close();
            System.out.println("Correo enviado exitosamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void readResponse(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println("S: " + line);
            if (line.charAt(3) == ' ') {
                break;
            }
        }
    }
}
