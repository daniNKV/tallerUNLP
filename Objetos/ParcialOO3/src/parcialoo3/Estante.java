/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parcialoo3;

/**
 *
 * @author dani
 */
public class Estante {
    private Libro[] estante;
    private int cantidadAlmacenados;
    
    public Estante(int capacidad){
        this.cantidadAlmacenados = 0;
        this.estante = new Libro[capacidad];
    }
    
    public void agregarLibro(Libro libro) {
        this.estante[this.cantidadAlmacenados] = libro;
        cantidadAlmacenados++;
    }
    
    public Libro sacarLibro(int posicion){
        Libro libro = this.estante[posicion];
        this.estante[posicion] = null;
        cantidadAlmacenados--;

        return libro;
    }
    
    public double calcularPesoEstante(){
        double total = 0;
        
        for(int i = 0; i<cantidadAlmacenados; i++){
            if (estante[i] != null) {
                total += estante[i].getPeso();
                
            }
        }
        
        return total;
    }
}
