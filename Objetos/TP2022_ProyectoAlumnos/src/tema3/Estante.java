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
    private int cantidad = 0;
    private Libro libros[];
    
    public Estante(int i) {
        libros = new Libro[i];
        this.setCapacidad(i);
        
    }
    private void setCapacidad(int capacidad){
        this.capacidad = capacidad;
    }
    
    public int getCantidad() {
        return cantidad;
    }

    public Libro getLibro(int i){
        return libros[i];
    }
    
    public boolean quedaEspacio() {
        return this.getCantidad() < this.capacidad;
    }
    
    public void agregarLibro(Libro nuevoLibro) {
        if (cantidad < capacidad) {
            libros[cantidad] = nuevoLibro;
            cantidad++;
        }
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
