/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tema2;
/*
- Se dispone de la clase Persona (en la carpeta tema2). Un objeto persona puede crearse
sin valores iniciales o enviando en el mensaje de creación el nombre, DNI y edad (en ese
orden). Un objeto persona responde a los siguientes mensajes:

    getNombre() retorna el nombre (String) de la persona
    getDNI() retorna el dni (int) de la persona
    getEdad() retorna la edad (int) de la persona
    setNombre(X) modifica el nombre de la persona al “String” pasado por parámetro (X)
    setDNI(X) modifica el DNI de la persona al “int” pasado por parámetro (X)
    setEdad(X) modifica la edad de la persona al “int” pasado por parámetro (X)
    toString() retorna un String que representa al objeto. Ej: “Mi nombre es Mauro,
mi DNI es 11203737 y tengo 70 años”

Realice un programa que cree un objeto persona con datos leídos desde teclado. Luego
muestre en consola la representación de ese objeto en formato String.
*/
/**
 *
 * @author dani
 */
import PaqueteLectura.Lector;
public class Ej01Leer {
    
    public static void main(String... args) {
        System.out.print("Ingrese nombre de la persona: ");
        String nombre = Lector.leerString();
        System.out.println();
        
        System.out.print("Ingrese DNI: ");
        int dni = Lector.leerInt();
        System.out.println();
        
        System.out.print("Ingrese edad: ");
        int edad = Lector.leerInt();
        System.out.println();
        
        Persona persona = new Persona(nombre, dni, edad);
        
        System.out.println();
        System.out.print(persona.toString());
        
    }
}
