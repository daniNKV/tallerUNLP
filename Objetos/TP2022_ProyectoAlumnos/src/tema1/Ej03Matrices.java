/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tema1;

//Paso 1. importar la funcionalidad para generar datos aleatorios
import PaqueteLectura.GeneradorAleatorio;
public class Ej03Matrices {

    public static void main(String[] args) {
	//Paso 2. iniciar el generador aleatorio     
         GeneradorAleatorio.iniciar();
        //Paso 3. definir la matriz de enteros de 5x5 e iniciarla con nros. aleatorios 
        int [][] matriz = new int [5][5];
        for (int i = 0; i < 5; i++ ){
            for (int j = 0; j < 5; j++){
                matriz[i][j] = GeneradorAleatorio.generarInt(10);
            }
        }
        //Paso 4. mostrar el contenido de la matriz en consola
        for (int i = 0; i < 5; i++){
            System.out.print("i=" + i + " ");
            for (int j = 0; j < 5; j++){
                System.out.print(matriz[i][j] + " ");   
            }
            System.out.println();
        }
        
        System.out.println();
        
        //Paso 5. calcular e informar la suma de los elementos de la fila 1
        int suma = 0;
        for (int i = 0; i < 5; i++){
            suma += matriz[0][i];
        }
        System.out.print(suma);
        
        System.out.println();
        
        //Paso 6. generar un vector de 5 posiciones donde cada posición j contiene la suma de los elementos de la columna j de la matriz. 
        //        Luego, imprima el vector.

        System.out.println();
        int [] vector = {0,0,0,0,0};
        
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 5; i++) {
                vector[j] = vector[j] + matriz[i][j];
            }
        }
        
        for (int i = 0; i < 5; i++) {
            System.out.print(vector[i] + " ");
        }
        //Paso 7. lea un valor entero e indique si se encuentra o no en la matriz. En caso de encontrarse indique su ubicación (fila y columna)
        //   y en caso contrario imprima "No se encontró el elemento".
        
        System.out.println();
        
        int valorBuscado = 2;
        
        boolean encontrado = false;
        int i = 0;
           
        while(encontrado == false && i < 5) {
            int j = 0;
            while (encontrado == false && j < 5){
                if (valorBuscado == matriz[i][j]) {
                    System.out.print("El valor se encuentra en la fila: " + i + " y columna: " + j);
                    encontrado = true;
                }else
                    j++;
            }
            i++;
        }
        if (encontrado == false) {
            System.out.print("El valor buscado no existe en la matriz");
        }
    }
}
