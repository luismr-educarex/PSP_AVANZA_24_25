package unidades.unidad2.ejemplos.sincronizacion.join;

class HiloEjemplo extends Thread {
    public int contadorProcesamiento = 0;

    HiloEjemplo(int contadorProcesamiento) {
        this.contadorProcesamiento = contadorProcesamiento;
        System.out.println("Hilo creado");

    }

    @Override
    public void run() {
        System.out.println("Hilo " + this.getName() + " iniciado");
        while (contadorProcesamiento > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Hilo " + this.getName() + " interrumpido");
            }
            contadorProcesamiento--;
            System.out.println("Dentro del hilo " + this.getName() + ", contadorProcesamiento = " + contadorProcesamiento);
        }
        System.out.println("Hilo " + this.getName() + " finalizando");
    }

    public static void main(String[] args) {
        Thread hilo2 = new HiloEjemplo(1);
        hilo2.start();
        System.out.println("Invocando join");
        try {
            hilo2.join();
        } catch (InterruptedException e) {
            System.out.println("Hilo principal interrumpido mientras esperaba el join");
        }
        System.out.println("Retornado de join");
        System.out.println("¿Está el hilo vivo?: " + hilo2.isAlive());
    }
}
