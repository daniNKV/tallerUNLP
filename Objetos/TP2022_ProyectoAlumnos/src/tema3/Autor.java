/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
/**
 *
 * @author dani
 */
public class Autor {
    private String nombre;
    private String biografia;
    private String origen;
    
    public Autor() {
        
    }
    
    public Autor(String nombre, String biografia, String origen){
        this.nombre = nombre;
        this.biografia = biografia;
        this.origen = origen;
    }
    
    public Autor(String nombre){
        this.nombre = nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setNombre2(String unNombre ){
        nombre = unNombre;
    }
    
    public void setBiografia(String biografia){
        this.biografia = biografia;
    }
    
    public void setOrigen(String origen){
        this.origen = origen;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public String getNombre2() {
        return nombre;
    }
    public String getBiografia(){
        return this.biografia;
    }
    public String getOrigen(){
        return this.origen;
    }
    public String toString(){
        String str = "El autor llamado " + this.getNombre() + " es de origen " + this.getBiografia() + " ";
        String str2 = "y su biografia es: " + this.getBiografia();
        return str + str2;
    }
    
    
}
