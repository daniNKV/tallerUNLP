/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/*
4- Se realizará un casting para un programa de TV. El casting durará a lo sumo 5 días y en
cada día se entrevistarán a 8 personas en distinto turno.
a) Simular el proceso de inscripción de personas al casting. A cada persona se le pide
nombre, DNI y edad y se la debe asignar en un día y turno de la siguiente manera: las
personas primero completan el primer día en turnos sucesivos, luego el segundo día y así
siguiendo. La inscripción finaliza al llegar una persona con nombre “ZZZ” o al cubrirse los
40 cupos de casting.
Una vez finalizada la inscripción:
b) Informar para cada día y turno el nombre de la persona a entrevistar.
NOTA: utilizar la clase Persona y pensar en la estructura de datos a utilizar.
*/
package tema2;
import PaqueteLectura.Lector;
/**
 *
 * @author dani
 */
/*
class Inscripcion extends Persona{
    private int i, j;
    private int cantidadInscriptos = 0;
    private String ultimoNombreIngresado = "";
    private Persona nuevaInscripcion[][] = new Persona[i][j];
    
    public Inscripcion(int cantidadDias, int cantidadPorDia) {
        i = cantidadDias;
        j = cantidadPorDia;
    }
    
    
    public void Agregar(int dia, int turno) {
        System.out.print("Ingrese nombre del entrevistado: ");
        ultimoNombreIngresado = Lector.leerString();
        
        if (ultimoNombreIngresado != "ZZZ" ) {
            nuevaInscripcion[dia][turno].setNombre(ultimoNombreIngresado);
            System.out.println();
            
            System.out.print("Ingrese DNI: ");
            nuevaInscripcion[dia][turno].setDNI(Lector.leerInt());

            System.out.println();

            System.out.print("Ingrese edad: "); 
            nuevaInscripcion[dia][turno].setEdad(Lector.leerInt());

            System.out.println();

            cantidadInscriptos++;
            
        }
    }
    
    public String getUltimoNombre() {
        return ultimoNombreIngresado;
    }
    
    public int getCantidadInscriptos() {
        return cantidadInscriptos;
    }
    public void MostrarInscriptos() {
        for (int i = 0; i < 5; i++){
            for(int j = 0; j < 8; j++) {
                System.out.print("Dia: " + i + " Turno: " + j);
                System.out.print(nuevaInscripcion[i][j].getNombre());
                System.out.println();
               
            }        
        }
    }
    
    public void setNombreInscripto(int dia, int turno, String unNombre) {
        nuevaInscripcion[dia][turno].setNombre(unNombre);
    }
}

*/

public class Ej04InscripcionCasting {
    public static void main(String... args) {
        int cupo = 40;
        String cierre = "ZZZ";
        int i = 0, j = 0; // i = dias de casting, j = personas a Entrevistar;
        Persona matrizInscriptos[][] = new Persona[5][8];
        String nombreIngresado= "";
        
        System.out.print("Ingrese nombre del entrevistado: ");
        nombreIngresado = Lector.leerString();
        
        System.out.println();
        
        while (i*j < cupo && nombreIngresado != cierre) {
            matrizInscriptos[i][j].setNombre(nombreIngresado);
           
            System.out.print("Ingrese DNI: ");
            matrizInscriptos[i][j].setDNI(Lector.leerInt());
            
            System.out.println();
           
            System.out.print("Ingrese edad: "); 
            matrizInscriptos[i][j].setEdad(Lector.leerInt());

            if (j < 7) {
                j++;
            }else {
                j = 0;
                i++;
            }

            System.out.print("Ingrese nombre del entrevistado: ");
            nombreIngresado = Lector.leerString();

        }
        
        for (int k = 0; k < 5; k++){
            for(int h = 0; h < 8; h++) {
                System.out.print("Dia: " + k + " Turno: " + h);
                System.out.print(matrizInscriptos[k][h].getNombre());
                System.out.println();
               
            }
                
        }

           
            
        
        
       
                
        
        

        
    }

}