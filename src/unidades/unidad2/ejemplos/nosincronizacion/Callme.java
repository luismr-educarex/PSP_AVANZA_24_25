package unidades.unidad2.ejemplos.nosincronizacion;
class Callme {
    void call(String msg) {
        System.out.print("[" + msg);
        try {
            Thread.sleep(1000);  // Pausa de 1 segundo para simular una llamada
        } catch (InterruptedException e) {
            System.out.println("Interrumpido");
        }
        System.out.println("]");
    }
}
