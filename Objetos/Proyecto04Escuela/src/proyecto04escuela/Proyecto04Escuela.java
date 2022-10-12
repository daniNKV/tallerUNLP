/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package proyecto04escuela;
import PaqueteLectura.GeneradorAleatorio;
/**
 *
 * @author dani
 */
public class Proyecto04Escuela {

    public static void main(String[] args) {
        GeneradorAleatorio.iniciar();
        CoroMatriz coroM = new CoroMatriz("John Williams The Concert", new Director(20,"John Williams", 40292, 60), 2, 3);
        CoroSemicircular coroC = new CoroSemicircular("Beethoven Tribute", new Director(5, "Johnnie Harris", 500060, 35), 3);
        
        
        for(int i=0; i<2; i++){
            for(int j=0; j<3; j++){
                coroM.agregarCorista(new Corista(GeneradorAleatorio.generarInt(10), GeneradorAleatorio.generarString(5), GeneradorAleatorio.generarInt(2000), GeneradorAleatorio.generarInt(90)));
               
            }
        }
        
        for (int j=0; j < 3 ; j++){
            coroC.agregarCorista(new Corista(GeneradorAleatorio.generarInt(10), GeneradorAleatorio.generarString(5), GeneradorAleatorio.generarInt(2000), GeneradorAleatorio.generarInt(90)));
        }
        
        System.out.println("coro M: " + coroM.estaOrdenado());
        System.out.println("coro C: " + coroC.estaOrdenado());
        System.out.println();
        System.out.print(coroM.toString());
        System.out.print(coroC.toString());
        
        coroM.toString();
        coroC.toString();
       
    }
}
