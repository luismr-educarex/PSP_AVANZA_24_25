package unidades.unidad2.ejemplos.productorconsumidor;
class Buffer {
    private int[] buffer;
    private int count = 0;
    private int in = 0;
    private int out = 0;
    private int size;

    public Buffer(int size) {
        this.size = size;
        buffer = new int[size];
    }

    public synchronized void producir(int item) throws InterruptedException {
        // Si el buffer está lleno, espera
        while (count == size) {
            System.out.println("Buffer lleno. Productor en espera...");
            wait();
        }
        
        // Añadir el elemento al buffer
        buffer[in] = item;
        in = (in + 1) % size;
        count++;
        System.out.println("Producido: " + item);

        // Notificar al consumidor que hay un nuevo elemento disponible
        notifyAll();
    }

    public synchronized int consumir() throws InterruptedException {
        // Si el buffer está vacío, espera
        while (count == 0) {
            System.out.println("Buffer vacío. Consumidor en espera...");
            wait();
        }

        // Retirar el elemento del buffer
        int item = buffer[out];
        out = (out + 1) % size;
        count--;
        System.out.println("Consumido: " + item);

        // Notificar al productor que hay espacio disponible
        notifyAll();
        return item;
    }
}