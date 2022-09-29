/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tema1;

/*
4- Un edificio de oficinas está conformado por 8 pisos (1..8) y 4 oficinas por piso
(1..4). Realice un programa que permita informar la cantidad de personas que
concurrieron a cada oficina de cada piso. Para esto, simule la llegada de personas al
edificio de la siguiente manera: a cada persona se le pide el nro. de piso y nro. de
oficina a la cual quiere concurrir. La llegada de personas finaliza al indicar un nro.
de piso 9. Al finalizar la llegada de personas, informe lo pedido.
*/

/**
 *
 * @author dani
 */

import PaqueteLectura.GeneradorAleatorio;
public class Ej04Oficinas {
    public static void main(String... args){
        GeneradorAleatorio.iniciar();
        int cantidadPisos = 9;
        int cantidadOficinas = 5;
        
        int cantidadIngresos = 0;
        int ingresoPiso = 1 + GeneradorAleatorio.generarInt(cantidadPisos);
        
        while(ingresoPiso != 9) {
            int ingresoOficina = 1 + GeneradorAleatorio.generarInt(cantidadOficinas);
            cantidadIngresos++;
            
            System.out.print("Ingreso a piso: " + ingresoPiso + " oficina: " + ingresoOficina);
            System.out.println();
            
            ingresoPiso = 1 + GeneradorAleatorio.generarInt(cantidadPisos);
        }
        System.out.println();
        System.out.print("Cantidad total de ingresos: " + cantidadIngresos);
    }
}
