/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package parcialoo;
/* 
1. Representar un concurso de baile. El concurso tiene a lo sumo N parejas. Cada pareja tiene2
participantes y un estilo de baile. De los participantes se tiene dni, nombre, edad.
a) Genere las clases necesarias. Provea constructores para iniciar el concurso para un máximo de N
parejas (inicialmente sin parejas cargadas); las parejas y los participantes a partir de toda su información.
b) Implemente métodos en las clases adecuadas para permitir:
Agregar una pareja al concurs0. Asuma que hay lugar.
Obtener la diferencia de edad de la pareja.
Obtener la pareja con más diferencia de edad del concurso
2. Realice un programa que instancie un concurso, cargue 2 parejas y a partir del concurso muestre: los
nombres de los participantes de la pareja con más diferencia de edad.
*/
/**
 *
 * @author dani
 */
public class ParcialOO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Concurso concurso = new Concurso(2);
        Participante p1 = new Participante("Juan", 20020, 20);
        Participante p2 = new Participante("Carla", 18009, 19);
        Participante p3 = new Participante("Ramon", 15002, 56);
        Participante p4 = new Participante("Laura", 12092, 59);
      
        Pareja pareja1 = new Pareja(p1, p2, "Rock");
        Pareja pareja2 = new Pareja(p3, p4, "Tango");
        
        concurso.agregarPareja(pareja1);
        concurso.agregarPareja(pareja2);
        
        System.out.print(concurso.getMayorDiferenciaEdad().toString());
    }
    
}
