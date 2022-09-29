/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
5- El dueño de un restaurante entrevista a cinco clientes y les pide que califiquen
(con puntaje de 1 a 10) los siguientes aspectos: (0) Atención al cliente (1) Calidad
de la comida (2) Precio (3) Ambiente.
Escriba un programa que lea desde teclado las calificaciones de los cinco clientes
para cada uno de los aspectos y almacene la información en una estructura. Luego
imprima la calificación promedio obtenida por cada aspecto
*/

package tema1;
import PaqueteLectura.Lector;
/**
 *
 * @author dani
 */
public class Ej05Restaurante {
    public static void main(String... args) {
        int clientes[][] = new int[5][4];
        double promedios[] = new double[4];
        
        int atencion = 0;
        int calidad = 1;
        int precio = 2;
        int ambiente = 3;
        
        
        for(int i = 0; i < 5; i++) {
            System.out.print("Cliente Número: " + (i+1));
            System.out.println();
            
            System.out.print("Puntuación de Atención al cliente: ");
            clientes[i][atencion] = Lector.leerInt();
            System.out.println();
            
            System.out.print("Puntuacion de la calidad de la comida: ");
            clientes[i][calidad] = Lector.leerInt();
            System.out.println();

            System.out.print("Puntuacion del precio: ");
            clientes[i][precio] = Lector.leerInt();
            System.out.println();

            System.out.print("Puntuacion del ambiente: ");
            clientes[i][ambiente] = Lector.leerInt();
            System.out.println();
        }
        
        for (int i = 0; i < 5; i++) {
            for(int j = 0; j < 4; j++) {
                System.out.print(clientes[i][j] + " ");
            }
            System.out.println();
        }
        
        for (int i = 0; i < 4; i++) {
            int total = 0;
            for (int j = 0; j < 5; j++) {
                total += clientes[j][i];
            }
            promedios[i] = total / 5;
        }
        
        for (int i = 0; i < 4; i++) {
            System.out.print("Promedio de categoria " + i + ": " + promedios[i]);
            System.out.println();
        }
    }
}
