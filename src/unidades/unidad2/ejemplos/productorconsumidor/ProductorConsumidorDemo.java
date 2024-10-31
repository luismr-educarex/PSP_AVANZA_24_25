package unidades.unidad2.ejemplos.productorconsumidor;
public class ProductorConsumidorDemo {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(5); // Tamaño del buffer de 5

        Productor productor = new Productor(buffer);
        Consumidor consumidor = new Consumidor(buffer);

        productor.start();
        consumidor.start();
    }
}