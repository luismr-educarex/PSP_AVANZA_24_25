/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unidades.unidad2.ejemplos.posiblehiloegoista;

public class Color extends Thread {
   //clase que extiende a Thread
    String color;
    public Color (String c){
        color=c;
    }
   public void run(){
       //se imprime 100 veces el valor de: color + i
       for(int i=1;i<=100;i++)
            System.out.println(color + i);
            Thread.yield(); //llamada a yield()
    }
}
