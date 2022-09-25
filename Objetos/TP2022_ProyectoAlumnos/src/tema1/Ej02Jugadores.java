
package tema1;

//Paso 1: Importar la funcionalidad para lectura de datos
import PaqueteLectura.GeneradorAleatorio;

public class Ej02Jugadores {
        
  
    public static void main(String[] args) {
        //Paso 2: Declarar la variable vector de double 
        int DF = 15;
        //Paso 3: Crear el vector para 15 double 
        double [] alturas = new double[DF];
        //Paso 4: Declarar indice y variables auxiliares a usar
         GeneradorAleatorio.generarDouble(2);
        //Paso 6: Ingresar 15 numeros (altura), cargarlos en el vector, ir calculando la suma de alturas
        double suma = 0;
        for (int i=0; i<DF; i++) {
            alturas[i] = GeneradorAleatorio.generarDouble(2);
            suma = suma + alturas[i];
        }
        suma = suma / DF;
        System.out.println("Promedio Alturas: " + suma);
        //Paso 8: Recorrer el vector calculando lo pedido (cant. alturas que están por encima del promedio)
        int cantAltos = 0;
        for (int i = 0; i < DF; i++) {
            if (alturas[i] > suma) {
                cantAltos = cantAltos + 1;
            }
        }
        //Paso 9: Informar la cantidad.
    }
    
}
