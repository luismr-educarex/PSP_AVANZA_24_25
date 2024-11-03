/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unidades.unidad2.ejemplos.executor;

import java.util.Random;

/******************************************************************************
 *
 */
public class NumerosAleatorios implements Runnable {

    /**************************************************************************
     * compone una cadena de diez números aleatorios menores que 50, separados
     * por ','
     */
    public void run() {

        String strReturn = "";
        Random random = new Random();

        for (int i = 0; i <10; i++) {
            strReturn += random.nextInt(50) + ", ";
            Thread.yield();
        }

        System.out.println("Números aleatorio obtenidos por "
                + Thread.currentThread().getName() + ": " + strReturn);
    }
}
