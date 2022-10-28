/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package parcialoo3;


/*
1) Queremos representar estanterías de libros. Una estantería mantiene sus libros organizados en N
estantes cada uno con lugar para M libros. Un libro posee título, nombre de su primer autor y peso.
a) Implemente las clases de su modelo, con sus atributos y getters/setters adecuados. Provea
constructores para iniciar: los libros a partir de toda su información; la estantería para N estantes y
lugar para M libros por estante (inicialmente no debe tener libros cargados).
b) Implemente los siguientes métodos:
- almacenarLibro: recibe un libro y un nro. de estante válido, y lo almacena en el primer lugar libre de
dicho estante. Asuma que hay espacio para almacenar el libro.
- sacarLibro: saca y devuelve el libro que se encuentra en el estante X, lugar Y (X e Y se reciben y
son válidos). Dicho lugar debe quedar disponible.
- calcular: calcula y devuelve el número del estante más pesado (teniendo en cuenta el peso de sus
libros).

2) Realice un programa que instancie una estantería para 5 estantes y 3 libros por estante. Almacene 7
libros en la estantería. A partir de la estantería: saque un libro e informe su representación String;
luego, informe el número de estante más pesado.
*/



/**
 *
 * @author dani
 */
public class ParcialOO3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Estanteria estanteria = new Estanteria(5, 3);
        
        estanteria.almacenarLibro(new Libro("lord of the rings", "tolkien", 500), 0);
        estanteria.almacenarLibro(new Libro("Back to the future", "supo", 600), 2);
        estanteria.almacenarLibro(new Libro("Harry Potter", "quien no debe", 100), 4);
        estanteria.almacenarLibro(new Libro("Cien años de soledad", "mmm", 900), 1);
        estanteria.almacenarLibro(new Libro("Las mil y una noches", "ni idea", 400), 0);
        estanteria.almacenarLibro(new Libro("Martin fierro", "alguien", 200), 0);
        estanteria.almacenarLibro(new Libro("Java and me", "asd", 200), 2);
        
        System.out.print(estanteria.sacarLibro(0, 1).toString());
        System.out.print(estanteria.calcularEstanteMasPesado());
    }   
    
}
