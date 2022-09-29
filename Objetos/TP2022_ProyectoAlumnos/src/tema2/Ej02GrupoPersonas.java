/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tema2;

/*
2- Utilizando la clase Persona. Realice un programa que almacene en un vector a lo sumo
15 personas. La información (nombre, DNI, edad) se debe generar aleatoriamente hasta
obtener edad 0. Luego de almacenar la información:
- Informe la cantidad de personas mayores de 65 años.
- Muestre la representación de la persona con menor DNI
*/

import PaqueteLectura.GeneradorAleatorio;
/**
 *
 * @author dani
 */
public class Ej02GrupoPersonas {
    
    public static void main(String... args) {
        GeneradorAleatorio.iniciar();
        
        int dimL = 0;
        int dimF = 15;
        int cantidadMayoresA65 = 0;
      
        Persona menorDni = new Persona();
        menorDni.setDNI(99999);
        
        Persona vectorPersonas[] = new Persona[dimF];

        Persona persona = new Persona(GeneradorAleatorio.generarString(5), GeneradorAleatorio.generarInt(20000), GeneradorAleatorio.generarInt(90) );
        
        while (persona.getEdad() != 0 && dimL < dimF) {
            vectorPersonas[dimL] = persona;
            dimL++;
        
            persona = new Persona(GeneradorAleatorio.generarString(5), GeneradorAleatorio.generarInt(20000), GeneradorAleatorio.generarInt(90) );   

        }   
        
        for (int i = 0; i < dimL; i++ ){
            System.out.print(i);
            System.out.println();
            System.out.print(vectorPersonas[i].toString());
            System.out.println();
        }
        
        for (int i = 0; i < dimL; i++) {
            if (vectorPersonas[i].getEdad() > 65) {
                cantidadMayoresA65++;
            }
            if (vectorPersonas[i].getDNI() < menorDni.getDNI()) {
                menorDni = vectorPersonas[i];
            }
        }
        
        System.out.print("Existen " + cantidadMayoresA65 + " personas mayores a 65 años");
        System.out.println();
        System.out.print("Persona almacenada con menor DNI: ");
        System.out.print(menorDni.toString());
    }
    
    
}
