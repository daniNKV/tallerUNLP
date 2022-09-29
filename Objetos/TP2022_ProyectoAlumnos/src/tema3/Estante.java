/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
almacenen como máximo N libros? ¿Cómo instanciaría el estante?*/


/**
 *
 * @author dani
 */
public class Estante {
    private int capacidad;
    private int cantidadLibros = 0;
    private Libro libros[];
    
    public Estante(int i) {
        Libro libros[] = new Libro[i];
        capacidad = i;
    }
    
    public int getCantidad() {
        return cantidadLibros;
    }
    public int getCapacidad() {
        return capacidad;
    }
    public Libro getLibro(int i){
        return libros[i];
    }
    
    public boolean quedaEspacio() {
        if (this.getCantidad() < this.getCapacidad()){
            return true;
        }else
            return false;
    }
    
    public void agregarLibro(Libro nuevoLibro) {
        libros[cantidadLibros] = nuevoLibro;
        cantidadLibros++;
       
    }
    
    public Libro buscarLibro(String nombre) {
        boolean encontrado = false;
        int i = 0;
        
        while (i < capacidad && encontrado == false) {
            if (nombre.equals(this.getLibro(i).getTitulo())){
                encontrado = true;
                System.out.print("Encontrado");
            } else {
                i++;
            }
            
        }
        
        if (encontrado == true) {
            return this.getLibro(i);
        } else
            return null;
       
    }
    
    
}
