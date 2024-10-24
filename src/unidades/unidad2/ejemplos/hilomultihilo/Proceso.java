package unidades.unidad2.ejemplos.hilomultihilo;

public class Proceso extends Thread{
	
	String mensaje;
	public Proceso(String msg) {
		super(msg);
	}
	
	public void run() {
		for(int i=0;i<15;i++) {
			System.out.println(mensaje);
		}
		
		System.out.println("Este proceso ha terminado:"+this.getName());
		
		
	}
	
	public void setMensaje(String msg) {
		this.mensaje=msg;
	}

}
