/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tema3;


/*
3-A- Defina una clase para representar estantes. Un estante almacena a lo sumo 20 libros.
Implemente un constructor que permita iniciar el estante sin libros. Provea métodos para:
(i) devolver la cantidad de libros que almacenados (ii) devolver si el estante está lleno
(iii) agregar un libro al estante (iv) devolver el libro con un título particular que se recibe.
B- Realice un programa que instancie un estante. Cargue varios libros. A partir del estante,
busque e informe el autor del libro “Mujercitas”.
C- Piense: ¿Qué modificaría en la clase definida para ahora permitir estantes que
almacenen como máximo N libros? ¿Cómo instanciaría el estante?
*/
/**
 *
 * @author dani
 */ 
import PaqueteLectura.GeneradorAleatorio;
public class Ej03Estantes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GeneradorAleatorio.iniciar();
        // TODO code application logic here
        Estante estante = new Estante(20);
        int i = 0;
        
        while (i < 5) {
            Libro libro = new Libro();

            if (i == 3) {
                libro.setTitulo("Mujercitas");
                libro.setPrimerAutor(new Autor("John"));
            }else {
                libro.setTitulo(GeneradorAleatorio.generarString(4));
                libro.setPrimerAutor(new Autor(GeneradorAleatorio.generarString(5)));
            }
               
            estante.agregarLibro(libro);
            
            i++;
        }
        
        System.out.print(estante.buscarLibro("Mujercitas").getPrimerAutor().getNombre());
  
    }
    
}
