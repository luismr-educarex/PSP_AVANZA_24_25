package unidades.unidad2.ejemplos.locks;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Calculator {
    private double result = 0;
    private final Lock lock = new ReentrantLock();

    public void add(double value) {
        lock.lock();
        try {
            result += value;
        } finally {
            lock.unlock();
        }
    }

    public void subtract(double value) {
        lock.lock();
        try {
            result -= value;
        } finally {
            lock.unlock();
        }
    }

    public double getResult() {
        lock.lock();
        try {
            return result;
        } finally {
            lock.unlock();
        }
    }
}
