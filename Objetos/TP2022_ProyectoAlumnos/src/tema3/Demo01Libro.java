/*
 Programa que instancia un objeto libro e inicializa sus v.i.s enviando mensajes set#
 */


package tema3;
/*
2-A- Modifique la clase Libro.java (carpeta tema3) para ahora considerar que el primer
autor es un objeto instancia de la clase Autor.
Implemente la clase Autor, sabiendo que se caracterizan por nombre, biografía y origen
y que deben permitir devolver/modificar el valor de sus atributos y devolver una
representación String formada por nombre, biografía y origen.
Luego realice las modificaciones necesarias en la clase Libro
*/



public class Demo01Libro {

    /*"Herbert Schildt"
     * http://www.amazon.com/Java-Beginners-Guide-Herbert-Schildt/dp/0071809252/ref=sr_1_3?s=books&ie=UTF8&qid=1434108310&sr=1-3
     * @param args
     */
    public static void main(String[] args) {
        Autor autor = new Autor("Herbert Schildt", "Uno de los autores más revolucionarios del mundo IT", "Austriaco");
        Libro libro = new Libro();
        libro.setTitulo("Java: A Beginner's Guide");
        libro.setEditorial("Mcgraw-Hill");
        libro.setAñoEdicion(2014);
        libro.setPrimerAutor(autor); // El arg tendria que ser Autores.getAutor("Herbert Schildt");
        libro.setISBN("978-0071809252");
        libro.setPrecio(21.72);
        System.out.println(libro.toString());
       // System.out.println(libro); /* invoca automaticamente al metodo toString */

    }
    
}
