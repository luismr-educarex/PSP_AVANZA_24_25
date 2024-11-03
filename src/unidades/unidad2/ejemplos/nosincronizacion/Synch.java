package unidades.unidad2.ejemplos.nosincronizacion;



public class Synch {
    public static void main(String args[]) {
        Callme target = new Callme();
        Caller ob1 = new Caller(target, "Hola");
        Caller ob2 = new Caller(target, "Sincronizado");
        Caller ob3 = new Caller(target, "Mundo");

        // Espera a que terminen los hilos
        try {
            ob1.t.join();
            ob2.t.join();
            ob3.t.join();
        } catch (InterruptedException e) {
            System.out.println("Interrumpido");
        }
    }
}
