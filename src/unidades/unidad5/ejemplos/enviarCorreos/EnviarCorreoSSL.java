package unidades.unidad5.ejemplos.enviarCorreos;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EnviarCorreoSSL {
    public static void main(String[] args) {
        String from = "ffffff"; // correo de origen
        String to = "tttttttt"; // correo destino
        String username = "usuario"; // cuenta del correo del usuario (igual que correo origen)
        String password = "xxxxxx";

        // Configuración para conexión SSL (Puerto 465)
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.fallback", "false");

        // Sesión con autenticación
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Crear mensaje
            Message mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(from));
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            mensaje.setSubject("Prueba de correo con SSL");
            mensaje.setText("Este es un mensaje enviado con SSLSocketFactory en Java.");

            // Enviar mensaje
            Transport.send(mensaje);
            System.out.println("Correo enviado con éxito.");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
