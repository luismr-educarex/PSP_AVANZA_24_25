package unidades.unidad5.ejemplos.inetaddress;



import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Para que este programa funcione correctamente tendrás que tener salida a
 * Internet mediante un router.
 * 
 * @author Luis Martínez Redondo
 */
public class Main {

  public static void main(String[] args) {
    try {
      // RED LOCAL
      System.out.println("********** LA RED LOCAL *************");

      // Obtiene el objeto InetAddress de localhost
      InetAddress objetoLocalhost = InetAddress.getByName("localhost");
      System.out.println("IP de localhost:");
      System.out.println(objetoLocalhost.getHostAddress());

      // Obtiene dirección de mi Equipo
      InetAddress miEquipoLan = InetAddress.getLocalHost();
      System.out.println("\nNombre de mi Equipo en la red local:");
      System.out.println(miEquipoLan.getHostName());
      System.out.println("\nIP de mi Equipo en la red local:");
      System.out.println(miEquipoLan.getHostAddress());

      // INTERNET
      System.out.println("\n********* INTERNET ************");

      // Obtener objeto InetAddress de www.wikipedia.org
      InetAddress objetoWikipedia = InetAddress.getByName("www.wikipedia.org");
      System.out.println("\nIP de www.wikipedia.org:");
      System.out.println(objetoWikipedia.getHostAddress());

      // Obtener objeto InetAddress de www.github.com
      InetAddress objetoGitHub = InetAddress.getByName("www.github.com");
      System.out.println("\nIP de www.github.com:");
      System.out.println(objetoGitHub.getHostAddress());

      // Obtener todas las IP asociadas a google.com
      InetAddress[] matrizAddress = InetAddress.getAllByName("www.google.com");
      System.out.println("\nImprime todas las IP disponibles para www.google.com: ");
      for (InetAddress address : matrizAddress) {
        System.out.println(address.getHostAddress());
      }
    } catch (UnknownHostException e) {
      System.out.println(e);
      System.out.println(
              "Parece que no estás conectado, o que el servidor DNS no ha "
              + "podido tramitar tu solicitud");
    }
  }
}
